package app.wooportal.server.core.security.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.core.security.dto.DefaultUserDetails;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

  private final UserService userService;

  public DefaultUserDetailsService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public DefaultUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> user = userService.getByMail(username);
    
    return user.isPresent()
        ? new DefaultUserDetails(user.get())
        : null;
  }

}
