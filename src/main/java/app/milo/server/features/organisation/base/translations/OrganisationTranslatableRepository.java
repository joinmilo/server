package app.milo.server.features.organisation.base.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface OrganisationTranslatableRepository
    extends TranslationRepository<OrganisationTranslatableEntity> {

}
