package app.wooportal.server.core.i18n.translation;

import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.core.i18n.components.language.LanguageEntity;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.repository.RepositoryService;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class TranslationService {

  private final RepositoryService repoService;
  
  private final LocaleService localeService;

  public TranslationService(
      RepositoryService repoService,
      LocaleService localeService) {
    this.repoService = repoService;
    this.localeService = localeService;
  }

  @SuppressWarnings("unchecked")
  public <T extends TranslatableEntity<?>> T getTranslatableInstance(
      Class<T> translatableClass,
      LanguageEntity currentWriteLanguage,
      Object parent) throws Throwable {
    var repo = repoService.getRepository(translatableClass);
    if (repo instanceof TranslationRepository<?>) {
      var translationRepo = (TranslationRepository<?>) repo;
      var findByLanguageAndParent = translationRepo.getClass().getMethod(
          "findByLanguageAndParent", LanguageEntity.class, parent.getClass().getSuperclass());

      var existingTranslatable = (T) findByLanguageAndParent.invoke(translationRepo,
          currentWriteLanguage, parent);

      return existingTranslatable != null
          ? existingTranslatable
          : (T) translatableClass.getDeclaredConstructor().newInstance();
    }
    throw new RuntimeException(
        "Repository of Translation must inherit from " + TranslationRepository.class);
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

}
