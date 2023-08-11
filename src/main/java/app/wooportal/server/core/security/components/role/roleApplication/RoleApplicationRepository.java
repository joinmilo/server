package app.wooportal.server.core.security.components.role.roleApplication;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
interface RoleApplicationRepository extends DataRepository<RoleApplicationEntity> {
  
}
