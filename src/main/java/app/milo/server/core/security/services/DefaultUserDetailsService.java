package app.milo.server.core.security.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import app.milo.server.core.security.components.user.UserEntity;
import app.milo.server.core.security.components.user.UserService;
import app.milo.server.core.security.dto.DefaultUserDetails;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

  private final UserService userService;

  public DefaultUserDetailsService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public DefaultUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> user = userService.getByMail(username);
    
    if (user.isPresent()) {
      return new DefaultUserDetails(user.get()); 
    }
    
    throw new UsernameNotFoundException("user does not exist with username: " + username);
  }

}
