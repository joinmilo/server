package app.wooportal.server.features.organisations.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface OrganisationCommentTranslatableRepository
    extends TranslationRepository<OrganisationTranslatableEntity> {
  
}
