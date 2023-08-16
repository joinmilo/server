package app.wooportal.server.core.security.components.role.privilege.application;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
interface PrivilegeApplicationRepository extends DataRepository<PrivilegeApplicationEntity> {
  
}
