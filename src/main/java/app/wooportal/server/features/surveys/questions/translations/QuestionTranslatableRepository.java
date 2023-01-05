package app.wooportal.server.features.surveys.questions.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface QuestionTranslatableRepository
    extends TranslationRepository<QuestionTranslatableEntity> {
  
}
