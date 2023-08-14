package app.wooportal.server.core.security.components.role.base;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
interface RoleRepository extends DataRepository<RoleEntity> {

}
