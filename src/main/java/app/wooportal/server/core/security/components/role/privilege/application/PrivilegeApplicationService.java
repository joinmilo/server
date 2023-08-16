package app.wooportal.server.core.security.components.role.privilege.application;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.role.privilege.RolePrivilegeService;

@Service
public class PrivilegeApplicationService
    extends DataService<PrivilegeApplicationEntity, PrivilegeApplicationPredicateBuilder> {

  public PrivilegeApplicationService(DataRepository<PrivilegeApplicationEntity> repo,
      PrivilegeApplicationPredicateBuilder predicate,
      RolePrivilegeService rolePrivilegeService) {
    super(repo, predicate);
    
    addService("privilege", rolePrivilegeService);
  }
}
