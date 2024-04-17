package app.milo.server.core.security.components.role.privilege;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
interface RolePrivilegeRepository extends DataRepository<RolePrivilegeEntity> {

}
