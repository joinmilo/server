package app.milo.server.core.security.services;

import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import app.milo.server.core.security.components.user.UserEntity;
import app.milo.server.core.security.components.user.UserService;
import app.milo.server.core.security.dto.DefaultUserDetails;

@Service
public class DefaultAuthorizationService extends AbstractAuthorizationService {
  
  public DefaultAuthorizationService(
      AuthenticationService authenticationService,
      UserService userService) {
    super(authenticationService, userService);
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
