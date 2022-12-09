package app.wooportal.server.components.feedbacks.comment.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.language.translation.TranslationRepository;

@Repository
public interface CommentTranslatableRepository
    extends TranslationRepository<CommentTranslatablesEntity> {
  
}
