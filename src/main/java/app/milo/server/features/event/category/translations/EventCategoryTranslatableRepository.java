package app.milo.server.features.event.category.translations;

import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface EventCategoryTranslatableRepository
    extends TranslationRepository<EventCategoryTranslatableEntity> {
  
}
