package app.wooportal.server.core.messaging.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;
import app.wooportal.server.features.events.translations.EventTranslatableEntity;

@Repository
public interface NotificationTranslatableRepository
    extends TranslationRepository<EventTranslatableEntity> {
  
}
