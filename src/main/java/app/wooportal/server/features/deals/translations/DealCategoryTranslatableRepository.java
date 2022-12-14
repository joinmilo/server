package app.wooportal.server.features.deals.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.language.translation.TranslationRepository;

@Repository
public interface DealCategoryTranslatableRepository
    extends TranslationRepository<DealTranslatableEntity> {

}
