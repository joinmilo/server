package app.milo.server.core.security.services;

import com.querydsl.core.BooleanBuilder;
import app.milo.server.core.security.components.user.UserService;

public abstract class AbstractAuthorizationService {

  protected final AuthenticationService authenticationService;
  protected final UserService userService;
  
  public AbstractAuthorizationService(
      AuthenticationService authenticationService,
      UserService userService) {
    this.authenticationService = authenticationService;
    this.userService = userService;
  }
  
  public boolean authenticatedUserHasPrivilege(String... privilegeCode) {
    var user = authenticationService.getAuthenticatedUser();
    
    if (user.isPresent() && privilegeCode != null) {
      var predicate = userService.getPredicate();
      var builder = new BooleanBuilder();
      
      for (var code: privilegeCode) {
        builder.or(predicate.withUserAndPrivilege(user.get().getId(), code));
      }
      
      builder.or(predicate.withUserAndPrivilege(user.get().getId(), "admin"));
      return userService.exists(builder);
    }
    return false;
  }
  
  public boolean authenticatedUserHasAdminPrivilege() {
    var user = authenticationService.getAuthenticatedUser();
    
    if (user.isPresent()) {      
      return userService.exists(userService.getPredicate().hasAnyAdminPrivilege(user.get().getId()));
    }
    
    return false;
  }
}
