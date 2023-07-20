package app.wooportal.server.core.i18n.translation;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.hibernate.Hibernate;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.i18n.components.language.LanguageEntity;
import app.wooportal.server.core.i18n.components.language.LanguageService;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.repository.RepositoryService;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class TranslationService {
  
  private final LanguageService languageService;
  
  private final LocaleService localeService;

  private final RepositoryService repoService;
  
  private final TranslationApiService translationApiService;

  public TranslationService(
      LanguageService languageService,
      LocaleService localeService,
      RepositoryService repoService,
      TranslationApiService translationApiService) {
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
  
  public void save(BaseEntity savedEntity ) throws Throwable {
    
    var sourceTranslatables = new HashMap<String, String>();
    Class<TranslatableEntity<BaseEntity>> translatableClass = null;
    
    String[] currentLocale = {"de"};
    var longestFieldLength = 0;
    for (var field : ReflectionUtils.getFields(savedEntity.getClass())) {
      
      if (ReflectionUtils.getAnnotation(field, Translatable.class).isPresent()) {        
        var value = ReflectionUtils.get(field.getName(), savedEntity);
        if (value.isPresent()) {
          if(value.get().toString().length() > longestFieldLength) {
            longestFieldLength = value.get().toString().length();
            currentLocale = translationApiService.detectLanguage(value.get().toString());
          };
          sourceTranslatables.put(field.getName(), (String) value.get());
        }
      }
      
      var type = TranslationUtils.getTranslatableFieldType(field);
      if (type.isPresent()) {
        translatableClass = type.get();
      }
    }
    
    translate(sourceTranslatables, translatableClass, savedEntity, currentLocale[0]);
  }

  private void translate(HashMap<String, String> sourceTranslatables,
      Class<TranslatableEntity<BaseEntity>> translatableClass, BaseEntity savedEntity,
      String currentLocale) throws Throwable {
    if (!sourceTranslatables.isEmpty() && translatableClass != null) {
      for (var language : languageService.readAll().getResult()) {
        var translatable = getTranslatableInstance(translatableClass, language, savedEntity);

        for (var source : sourceTranslatables.entrySet()) {

          if (!translatable.getLanguage().getLocale().equals(currentLocale)) {
            
            var doc = Jsoup.parse(source.getValue()).body();
            
            for(var element : doc.select("*")) {
              if (element.ownText() != null && element.ownText().length() > 0) {
              var translation =
                translationApiService.translate(element.ownText(),
                    translatable.getLanguage().getLocale());
             element.text(translation.getTranslated()[0]);
              }
            }
           
            if (doc.hasText()) {
              translatable.set(source.getKey(), doc.html().toString());

            }
          } else {
            translatable.set(source.getKey(), source.getValue());
          }
        }
        translatable.set("id", UUID.randomUUID().toString());
        repoService.save(translatable);
      }
    }
  }
  
  @SuppressWarnings("unchecked")
  public <P extends BaseEntity, T extends TranslatableEntity<P>> T getTranslatableInstance(
      Class<T> translatableClass,
      LanguageEntity language,
      P parent) throws Throwable {
    var repo = repoService.getRepository(translatableClass);
    language = Hibernate.unproxy(language, LanguageEntity.class);
    
    if (repo instanceof TranslationRepository<?>) {
      var translationRepo = (TranslationRepository<?>) repo;
      var findByLanguageAndParent = translationRepo.getClass().getMethod(
          "findByLanguageIdAndParentId", String.class, String.class);

      var translatable = (T) findByLanguageAndParent.invoke(translationRepo, language.getId(), parent.getId());

      if (translatable != null) {
        return translatable;
      }
      
      translatable = (T) translatableClass.getDeclaredConstructor().newInstance();
      translatable.setLanguage(language);
      translatable.setParent(parent);
      
      return translatable;
            
    }
    throw new RuntimeException(
        "Repository of Translation must inherit from " + TranslationRepository.class);
  }
}
