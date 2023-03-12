package app.wooportal.server.base.feedback.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface FeedbackTranslatableRepository
    extends TranslationRepository<FeedbackTranslatableEntity> {
}
