package app.wooportal.server.base.userContext.security;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.core.security.components.role.privilege.RolePrivilegeService;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.core.security.services.AbstractAuthorizationService;
import app.wooportal.server.core.security.services.AuthenticationService;

@Service
public class UserContextAuthorizationService extends AbstractAuthorizationService {
  
  public UserContextAuthorizationService(
      AuthenticationService authenticationService,
      UserService userService) {
    super(authenticationService, userService);
  }

  public Optional<UserContextEntity> getUserContext(Authentication authentication) {
    var userDetails = getUserDetails(authentication);
    
    return userDetails.isPresent()
        ? Optional.ofNullable(userDetails.get().getUserContext())
        : Optional.empty();
  }
  
  public Optional<UserContextDetails> getUserDetails(Authentication authentication) {
    if (authentication.getPrincipal() instanceof UserContextDetails) {
      return Optional.ofNullable((UserContextDetails) authentication.getPrincipal());
    }
    return Optional.empty();
  }
  
  public Optional<UserContextEntity> getAuthenticatedUserContext() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal() instanceof UserContextDetails) {
      var userContextDetails = (UserContextDetails) authentication.getPrincipal();
      return Optional.of(userContextDetails.getUserContext());
    }
    return Optional.empty();
  }

}
