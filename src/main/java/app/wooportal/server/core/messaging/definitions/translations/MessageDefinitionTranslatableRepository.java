package app.wooportal.server.core.messaging.definitions.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface MessageDefinitionTranslatableRepository
    extends TranslationRepository<MessageDefinitionTranslatableEntity> {
  
}
