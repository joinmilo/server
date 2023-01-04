package app.wooportal.server.core.security.components.role.translation;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface RoleTranslatableRepository extends TranslationRepository<RoleTranslatableEntity> {

}
