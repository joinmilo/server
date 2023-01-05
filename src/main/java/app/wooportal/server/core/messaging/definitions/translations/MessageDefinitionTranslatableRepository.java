package app.wooportal.server.core.messaging.definitions.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;
import app.wooportal.server.features.events.translations.EventTranslatableEntity;

@Repository
public interface MessageDefinitionTranslatableRepository
    extends TranslationRepository<EventTranslatableEntity> {
  
}
