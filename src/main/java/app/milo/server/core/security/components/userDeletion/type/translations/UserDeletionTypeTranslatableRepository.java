package app.milo.server.core.security.components.userDeletion.type.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface UserDeletionTypeTranslatableRepository
    extends TranslationRepository<UserDeletionTypeTranslatableEntity> {
  
}
