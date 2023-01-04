package app.wooportal.server.base.cms.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface FeatureTranslatableRepository
    extends TranslationRepository<LandingTranslatableEntity> {

}
