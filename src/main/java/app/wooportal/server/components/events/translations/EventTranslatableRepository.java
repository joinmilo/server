package app.wooportal.server.components.events.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.language.translation.TranslationRepository;

@Repository
public interface EventTranslatableRepository
    extends TranslationRepository<EventTranslatablesEntity> {
  
}
