package app.wooportal.server.features.organisations.organisationComments.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;
import app.wooportal.server.features.organisations.base.translations.OrganisationTranslatableEntity;

@Repository
public interface OrganisationCommentTranslatableRepository
    extends TranslationRepository<OrganisationTranslatableEntity> {
  
}
