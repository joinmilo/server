package app.wooportal.server.features.surveys.surveyResult.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface SurveyResultTranslatableRepository
    extends TranslationRepository<SurveyResultTranslatableEntity> {
  
}
