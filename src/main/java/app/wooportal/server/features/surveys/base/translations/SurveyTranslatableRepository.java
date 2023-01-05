package app.wooportal.server.features.surveys.base.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface SurveyTranslatableRepository
    extends TranslationRepository<SurveyTranslatableEntity> {
  
}
