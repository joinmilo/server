package app.wooportal.server.core.security.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.wooportal.server.core.security.JwtUserDetails;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.components.user.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  private final UserService userService;

  public JwtUserDetailsService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> user = userService.getByMail(username);
    
    return user.isPresent()
        ? new JwtUserDetails(user.get())
        : null;
  }

}
