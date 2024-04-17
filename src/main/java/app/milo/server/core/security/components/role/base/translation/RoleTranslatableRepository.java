package app.milo.server.core.security.components.role.base.translation;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface RoleTranslatableRepository extends TranslationRepository<RoleTranslatableEntity> {

}
