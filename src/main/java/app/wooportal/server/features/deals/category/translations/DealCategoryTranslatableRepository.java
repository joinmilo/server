package app.wooportal.server.features.deals.category.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;
import app.wooportal.server.features.deals.base.translations.DealTranslatableEntity;

@Repository
public interface DealCategoryTranslatableRepository
    extends TranslationRepository<DealTranslatableEntity> {

}
