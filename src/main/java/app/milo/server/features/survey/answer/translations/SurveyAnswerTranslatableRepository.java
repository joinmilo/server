package app.milo.server.features.survey.answer.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface SurveyAnswerTranslatableRepository
    extends TranslationRepository<SurveyAnswerTranslatableEntity> {
  
}
