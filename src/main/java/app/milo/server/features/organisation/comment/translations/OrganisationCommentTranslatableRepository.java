package app.milo.server.features.organisation.comment.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;
import app.milo.server.features.organisation.base.translations.OrganisationTranslatableEntity;

@Repository
public interface OrganisationCommentTranslatableRepository
    extends TranslationRepository<OrganisationTranslatableEntity> {
  
}
