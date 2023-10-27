package app.wooportal.server.core.security.services;

import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.security.components.role.privilege.RolePrivilegeService;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.dto.DefaultUserDetails;

@Service
public class DefaultAuthorizationService extends AbstractAuthorizationService {
  
  public DefaultAuthorizationService(
      AuthenticationService authenticationService,
      RolePrivilegeService rolePrivilegeService) {
    super(authenticationService, rolePrivilegeService);
  }

  public Optional<UserEntity> getUser(Authentication authentication) {
    var userDetails = getUserDetails(authentication);
    
    return userDetails.isPresent()
        ? Optional.ofNullable(userDetails.get().getUser())
        : Optional.empty();
  }
  
  public Optional<DefaultUserDetails> getUserDetails(Authentication authentication) {
    if (authentication.getPrincipal() instanceof DefaultUserDetails) {
      return Optional.ofNullable((DefaultUserDetails) authentication.getPrincipal());
    }
    return Optional.empty();
  }

}
