package app.wooportal.server.features.events.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.language.translation.TranslationRepository;

@Repository
public interface EventCategoryTranslatableRepository
    extends TranslationRepository<EventTranslatableEntity> {
  
}
