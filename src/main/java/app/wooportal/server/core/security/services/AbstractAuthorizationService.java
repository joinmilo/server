package app.wooportal.server.core.security.services;

import com.querydsl.core.BooleanBuilder;

import app.wooportal.server.core.security.components.role.privilege.RolePrivilegeService;

public abstract class AbstractAuthorizationService {

  protected final AuthenticationService authenticationService;
  protected final RolePrivilegeService rolePrivilegeService;
  
  public AbstractAuthorizationService(
      AuthenticationService authenticationService,
      RolePrivilegeService rolePrivilegeService) {
    this.authenticationService = authenticationService;
    this.rolePrivilegeService = rolePrivilegeService;
  }
  
  public boolean authenticatedUserHasPrivilege(String... privilegeCode) {
    var user = authenticationService.getAuthenticatedUser();
    
    if (user.isPresent() && privilegeCode != null) {
      var predicate = rolePrivilegeService.getPredicate();
      var builder = new BooleanBuilder();
      
      for (var code: privilegeCode) {
        builder.or(predicate.withUserAndCode(user.get().getId(), code));
      }

      return rolePrivilegeService.exists(builder);
    }
    return false;
  }
}
