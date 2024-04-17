package app.milo.server.base.userContext.security;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import app.milo.server.base.userContext.base.UserContextService;

@Primary
@Service
public class UserContextDetailsService implements UserDetailsService {
  
  private final UserContextService userContextService;

  public UserContextDetailsService(
      UserContextService userContextService) {
    this.userContextService = userContextService;
  }

  @Override
  public UserContextDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userContextService.getByEmail(username);
    
    if (user.isPresent()) {
      return new UserContextDetails(user.get());
    }
    
    throw new UsernameNotFoundException("user does not exist with username: " + username);
  }
  
}
