package app.wooportal.server.core.messaging.templates.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface MessageTemplateTranslatableRepository
    extends TranslationRepository<MessageTemplateTranslatableEntity> {
  
}
