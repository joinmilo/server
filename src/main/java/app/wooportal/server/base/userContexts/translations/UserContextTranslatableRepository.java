package app.wooportal.server.base.userContexts.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface UserContextTranslatableRepository
    extends TranslationRepository<UserContextTranslatableEntity> {

}
