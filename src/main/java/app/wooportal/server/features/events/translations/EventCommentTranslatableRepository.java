package app.wooportal.server.features.events.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface EventCommentTranslatableRepository
    extends TranslationRepository<EventTranslatableEntity> {
  
}
