package app.wooportal.server.core.security.components.role.rolePrivilege;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class RolePrivilegeService
    extends DataService<RolePrivilegeEntity, RolePrivilegePredicateBuilder> {

  public RolePrivilegeService(DataRepository<RolePrivilegeEntity> repo,
      RolePrivilegePredicateBuilder predicate) {
    super(repo, predicate);
   
  }
}
