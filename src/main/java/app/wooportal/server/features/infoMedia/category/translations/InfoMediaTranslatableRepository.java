package app.wooportal.server.features.infoMedia.category.translations;

import org.springframework.stereotype.Repository;

import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface InfoMediaTranslatableRepository
    extends TranslationRepository<InfoMediaCategoryTranslatableEntity> {
  
}
