package app.wooportal.server.features.survey.answer.translations;


import org.springframework.stereotype.Repository;

import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface SurveyAnswerTranslatableRepository
    extends TranslationRepository<SurveyAnswerTranslatableEntity> {
  
}
