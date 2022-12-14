package app.wooportal.server.base.cms.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.language.translation.TranslationRepository;

@Repository
public interface LandingTranslatableRepository
    extends TranslationRepository<LandingTranslatableEntity> {

}
