package app.wooportal.server.core.messaging.notifications.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface NotificationTranslatableRepository
    extends TranslationRepository<NotificationTranslatableEntity> {
  
}
