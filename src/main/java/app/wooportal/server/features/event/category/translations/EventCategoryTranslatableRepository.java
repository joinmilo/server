package app.wooportal.server.features.event.category.translations;

import org.springframework.stereotype.Repository;

import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface EventCategoryTranslatableRepository
    extends TranslationRepository<EventCategoryTranslatableEntity> {
  
}
