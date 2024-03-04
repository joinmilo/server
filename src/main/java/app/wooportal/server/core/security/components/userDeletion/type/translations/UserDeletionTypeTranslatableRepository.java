package app.wooportal.server.core.security.components.userDeletion.type.translations;


import org.springframework.stereotype.Repository;

import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface UserDeletionTypeTranslatableRepository
    extends TranslationRepository<UserDeletionTypeTranslatableEntity> {
  
}
