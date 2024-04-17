package app.milo.server.features.event.comment.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface EventCommentTranslatableRepository extends TranslationRepository<EventCommentTranslatableEntity> {
  
}
