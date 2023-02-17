package app.wooportal.server.core.i18n.translation;

import java.lang.reflect.Method;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.i18n.language.LanguageEntity;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.repository.RepositoryService;

@Service
public class TranslationService {

  private final RepositoryService repoService;

  public TranslationService(RepositoryService repoService) {
    this.repoService = repoService;
  }

  //TODO: Crate proper translation service
  @SuppressWarnings("unchecked")
  public <T extends TranslatableEntity<?>> T getTranslatableInstance(
      Class<T> translatableClass,
      LanguageEntity currentWriteLanguage,
      Object parent) throws Throwable {
    DataRepository<T> repo = repoService.getRepository(translatableClass);
    if (repo instanceof TranslationRepository<?>) {
      TranslationRepository<?> translationRepo = (TranslationRepository<?>) repo;
      Method findByLanguageAndParent = translationRepo.getClass().getMethod(
          "findByLanguageAndParent", LanguageEntity.class, parent.getClass().getSuperclass());

      T existingTranslatable = (T) findByLanguageAndParent.invoke(translationRepo,
          currentWriteLanguage, parent);

      return existingTranslatable != null ? existingTranslatable
          : (T) translatableClass.getDeclaredConstructor().newInstance();
    }
    throw new RuntimeException(
        "Repository of Translation must inherit from " + TranslationRepository.class);
  }

}
