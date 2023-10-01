package app.wooportal.server.base.userContext.security;

import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import app.wooportal.server.base.userContext.base.UserContextEntity;

@Service
public class UserContextAuthorizationService {
  
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
