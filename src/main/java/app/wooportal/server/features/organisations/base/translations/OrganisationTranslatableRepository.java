package app.wooportal.server.features.organisations.base.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface OrganisationTranslatableRepository
    extends TranslationRepository<OrganisationTranslatableEntity> {

}
