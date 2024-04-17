package app.milo.server.core.security.components.role.application;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
interface PrivilegeApplicationRepository extends DataRepository<PrivilegeApplicationEntity> {
  
}
