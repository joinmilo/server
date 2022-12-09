package app.wooportal.server.components.categories.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.language.translation.TranslationRepository;

@Repository
public interface CategoryTranslatableRepository
    extends TranslationRepository<CategoryTranslatablesEntity> {
  
}
