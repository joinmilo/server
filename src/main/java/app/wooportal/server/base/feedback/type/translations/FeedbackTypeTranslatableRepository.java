package app.wooportal.server.base.feedback.type.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface FeedbackTypeTranslatableRepository
    extends TranslationRepository<FeedbackTypeTranslatableEntity> {
}
