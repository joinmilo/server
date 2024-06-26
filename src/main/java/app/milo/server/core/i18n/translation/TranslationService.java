package app.milo.server.core.i18n.translation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Parser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.error.ErrorMailService;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.core.i18n.components.language.LanguageEntity;
import app.milo.server.core.i18n.components.language.LanguageService;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.core.repository.RepositoryService;
import app.milo.server.core.utils.ReflectionUtils;

@Service
public class TranslationService {
  
  private final ErrorMailService errorMailService;

  private final LanguageService languageService;

  private final LocaleService localeService;

  private final RepositoryService repoService;

  private final TranslationApiService translationApiService;  

  public TranslationService(LanguageService languageService, LocaleService localeService,
      RepositoryService repoService, TranslationApiService translationApiService,
      ErrorMailService errorMailService) {
    this.errorMailService = errorMailService;
    this.languageService = languageService;
    this.localeService = localeService;
    this.repoService = repoService;
    this.translationApiService = translationApiService;

  }

  public void localizeList(List<?> list) throws Throwable {
    var locales = localeService.getCurrentRequestLocales();
    for (var entity : list) {
      localize(entity, locales);
    }
  }

  public void localizeSingle(Object entity) throws Throwable {
    if (!ReflectionUtils.getFieldsWithAnnotation(entity.getClass(), Translatable.class).isEmpty()) {
      localize(entity, localeService.getCurrentRequestLocales());
    }
  }

  private void localize(Object entity, List<String> locales) throws Throwable {
    fields: for (var field : ReflectionUtils.getFieldsWithAnnotation(entity.getClass(),
        Translatable.class)) {
      for (var locale : locales) {
        var translatables = TranslationUtils.getTranslatables(entity, locale);

        if (translatables.isPresent()) {
          for (var translatableField : translatables.get().getClass().getDeclaredFields()) {
            if (translatableField.getName().equals(field.getName())) {
              translatableField.setAccessible(true);

              var translation = translatableField.get(translatables.get());
              if (translation != null) {
                field.setAccessible(true);
                field.set(entity, translation);
                continue fields;
              }
            }
          }
        }
      }
    }
  }
  
  @Transactional
  public String saveDefaultTranslations(BaseEntity savedEntity) {
    var dto = createDto(savedEntity);
    
    if (dto.getSourceFields() != null && !dto.getSourceFields().isEmpty() && dto.getTranslatableClass() != null) {      
      var defaultLocales = localeService.getCurrentRequestLocales();
      if (defaultLocales != null && !defaultLocales.isEmpty()) {
        for (var locale: defaultLocales) {
          var language = languageService.readOne(languageService.singleQuery(
              languageService.getPredicate().withActive())
              .and(languageService.getPredicate().withLocale(locale)));
          
          if (language.isPresent()) {
            try {
              var translatable = getTranslatableInstance(dto.getTranslatableClass(), language.get(), savedEntity);
              if (translatable != null) {      
                for (var source : dto.getSourceFields().entrySet()) {
                  translatable.set(source.getKey(), source.getValue());
                }
                repoService.save(translatable);
                return locale;
              }
            } catch (Throwable e) {
              e.printStackTrace();
              errorMailService.sendErrorMail(e);
            }
          }
        }
      }
    }
    return null;
  }

  @Async
  public CompletableFuture<Boolean> saveAutoTranslations(BaseEntity savedEntity, String savedDefaultLocale) throws Throwable {
    try {      
      var dto = createDto(savedEntity);
      
      if (dto.getSourceFields() != null && ! dto.getSourceFields().isEmpty() && dto.getTranslatableClass() != null) {
        
        var translatables = new ArrayList<TranslatableEntity<BaseEntity>>();
        var locale = detectLocale(dto.getLongestContent());
        
        for (var language : languageService.readAll(
            languageService.collectionQuery(
                languageService.getPredicate().withActive()).and(
                    languageService.getPredicate().withoutLocale(savedDefaultLocale))
            ).getList()) {
          var translatable = getTranslatableInstance(dto.getTranslatableClass(), language, savedEntity);
          
          if (translatable != null) {
            translatables.add(translatable);
          }
        }
        
        if (locale.isPresent()) {
          persistAutoTranslations(translatables, dto.getSourceFields(), locale.get());
        }
        
      }

      return CompletableFuture.completedFuture(true);
    } catch (Throwable e) {
      e.printStackTrace();
      errorMailService.sendErrorMail(e);
      
      return CompletableFuture.completedFuture(false);
    }
  }

  private TranslationDto createDto(BaseEntity savedEntity) {
    var dto = new TranslationDto();

    for (var field : ReflectionUtils.getFields(savedEntity.getClass())) {

      if (ReflectionUtils.getAnnotation(field, Translatable.class).isPresent()) {
        var value = ReflectionUtils.get(field.getName(), savedEntity);
        if (value.isPresent()) {
          dto.putSourcField(field.getName(), (String) value.get());
        }
      }
      
      var type = TranslationUtils.getTranslatableFieldType(field);
      if (type.isPresent()) {
        dto.setTranslatableClass(type.get());
      }
    }
    
    return dto;
  }

  private Optional<String> detectLocale(String content) {
    try {
    var detectedLocales = translationApiService.detectLanguage(content);
    return detectedLocales.length > 0
        ? Optional.of(detectedLocales[0])
        : Optional.empty();
    } catch(Throwable e) {
      e.printStackTrace();
      errorMailService.sendErrorMail(e);
      return Optional.empty();
    }
  }

  private void persistAutoTranslations(
      List<TranslatableEntity<BaseEntity>> translatables,
      Map<String, String> sourceFields,
      String detectedLocale) throws Throwable {

    if (!sourceFields.isEmpty()) {
      
      for (var translatable : translatables) {
        for (var source : sourceFields.entrySet()) {

          var doc = Jsoup.parse(Parser.unescapeEntities((source.getValue()), true));

          var body = doc.body();
          if (body != null) {
            translate(body, translatable.getLanguage().getLocale());
          }

          var translatedText = doc.body().html();
          if (!translatedText.isEmpty()) {
            translatable.set(source.getKey(), translatedText);
          }
        }
        repoService.save(translatable);
      }

    }
  }

  private void translate(Node node, String locale) throws Throwable {
    if (node instanceof TextNode) {
      TextNode textNode = (TextNode) node;
      String text = textNode.getWholeText();
      if (!text.isEmpty()) {
          var translation = translationApiService.translate(text, locale);
          textNode.text(translation.getTranslated()[0]);    
      }
    } else if (node instanceof Element) {
      for (var child : node.childNodes()) {
        translate(child, locale);
      }
    }
  }

  @SuppressWarnings("unchecked")
  public <P extends BaseEntity, T extends TranslatableEntity<P>> T getTranslatableInstance(
      Class<T> translatableClass, LanguageEntity language, P parent) throws Throwable {
    var repo = repoService.getRepository(translatableClass);
    language = Hibernate.unproxy(language, LanguageEntity.class);

    if (repo instanceof TranslationRepository<?>) {
      var translationRepo = (TranslationRepository<?>) repo;
      var findByLanguageAndParent = translationRepo.getClass()
          .getMethod("findByLanguageIdAndParentId", String.class, String.class);

      var translatable =
          (T) findByLanguageAndParent.invoke(translationRepo, language.getId(), parent.getId());

      if (translatable != null) {
        return translatable;
      }

      translatable = (T) translatableClass.getDeclaredConstructor().newInstance();
      translatable.setLanguage(language);
      translatable.setParent(parent);
      translatable.setId(UUID.randomUUID().toString());

      return translatable;

    }
    throw new RuntimeException(
        "Repository of Translation must inherit from " + TranslationRepository.class);
  }

}
