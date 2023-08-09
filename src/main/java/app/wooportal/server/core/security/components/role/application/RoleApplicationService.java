package app.wooportal.server.core.security.components.role.application;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.role.RoleService;

@Service
public class RoleApplicationService
    extends DataService<RoleApplicationEntity, RoleApplicationPredicateBuilder> {

  public RoleApplicationService(DataRepository<RoleApplicationEntity> repo,
      RoleApplicationPredicateBuilder predicate,
      RoleService roleService) {
    super(repo, predicate);
    
    addService("role", roleService);
  }
}
