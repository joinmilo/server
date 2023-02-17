package app.wooportal.server.features.events.comments.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface EventCommentTranslatableRepository extends TranslationRepository<EventCommentTranslatableEntity> {
  
}
