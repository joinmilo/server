package app.wooportal.server.features.survey.question.translations;


import org.springframework.stereotype.Repository;

import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface SurveyQuestionTranslatableRepository
    extends TranslationRepository<SurveyQuestionTranslatableEntity> {
  
}
