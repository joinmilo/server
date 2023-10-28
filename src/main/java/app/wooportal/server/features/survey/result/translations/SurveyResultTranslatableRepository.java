package app.wooportal.server.features.survey.result.translations;


import org.springframework.stereotype.Repository;

import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface SurveyResultTranslatableRepository
    extends TranslationRepository<SurveyResultTranslatableEntity> {
  
}
