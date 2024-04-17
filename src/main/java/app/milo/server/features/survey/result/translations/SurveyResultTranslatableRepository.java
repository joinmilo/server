package app.milo.server.features.survey.result.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface SurveyResultTranslatableRepository
    extends TranslationRepository<SurveyResultTranslatableEntity> {
  
}
