package app.milo.server.features.survey.questionOption.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface SurveyQuestionOptionTranslatableRepository
    extends TranslationRepository<SurveyQuestionOptionTranslatableEntity> {
  
}
