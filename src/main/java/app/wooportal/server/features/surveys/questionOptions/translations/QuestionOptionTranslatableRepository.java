package app.wooportal.server.features.surveys.questionOptions.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface QuestionOptionTranslatableRepository
    extends TranslationRepository<QuestionOptionTranslatableEntity> {
  
}
