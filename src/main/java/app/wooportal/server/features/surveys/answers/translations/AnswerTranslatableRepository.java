package app.wooportal.server.features.surveys.answers.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface AnswerTranslatableRepository
    extends TranslationRepository<AnswerTranslatableEntity> {
  
}
