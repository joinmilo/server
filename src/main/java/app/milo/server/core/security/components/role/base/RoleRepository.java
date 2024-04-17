package app.milo.server.core.security.components.role.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
interface RoleRepository extends DataRepository<RoleEntity> {

}
