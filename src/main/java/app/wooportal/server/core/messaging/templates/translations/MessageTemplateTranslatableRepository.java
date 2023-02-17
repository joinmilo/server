package app.wooportal.server.core.messaging.templates.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;
import app.wooportal.server.features.events.base.translations.EventTranslatableEntity;

@Repository
public interface MessageTemplateTranslatableRepository
    extends TranslationRepository<EventTranslatableEntity> {
  
}
