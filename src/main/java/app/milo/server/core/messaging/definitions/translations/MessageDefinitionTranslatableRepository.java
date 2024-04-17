package app.milo.server.core.messaging.definitions.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface MessageDefinitionTranslatableRepository
    extends TranslationRepository<MessageDefinitionTranslatableEntity> {
  
}
