package app.milo.server.core.security.services;

import java.util.Collections;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import app.milo.server.base.userContext.security.UserContextDetails;
import app.milo.server.core.security.components.token.TokenService;
import app.milo.server.core.security.components.user.UserEntity;
import app.milo.server.core.security.dto.UserDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class AuthenticationService {
  
  private final TokenService tokenService;

  private final UserDetailsService userDetailsService;
  
  private final AuthenticationManager authManager;

  @Lazy
  public AuthenticationService(
      AuthenticationManager authManager,
      TokenService tokenService,
      UserDetailsService userDetailsService) {
    this.authManager = authManager;
    this.tokenService = tokenService;
    this.userDetailsService = userDetailsService;
  }

  public Optional<UserDetails> authenticate(String username, String password) {
    return Optional.of((UserDetails) authManager
        .authenticate(
            new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()))
        .getPrincipal());
  }
  
  public Optional<UserDetails> authenticateCurrentUser(String password) {
    var currentUser = getAuthenticatedUser();
    return currentUser.isPresent()
        ? authenticate(currentUser.get().getEmail(), password)
        : Optional.empty();
  }
  
  public Optional<UsernamePasswordAuthenticationToken> getAuthenticationToken(HttpServletRequest request) {
    if (request != null) {
      try {
        var jwtToken = request.getHeader("Authorization");
        var userDetails = retrieveUserDetailsFromToken(jwtToken);
        if (userDetails.isPresent()) {
          return Optional.of(new UsernamePasswordAuthenticationToken(
              userDetails.get(), null, userDetails.get().getAuthorities())); 
        }
      } catch (Exception ignored) {}
    }
    return Optional.empty();
  }
  
  private Optional<UserDetails> retrieveUserDetailsFromToken(String jwtToken) {
    if (jwtToken != null && !jwtToken.contains("undefined")) {
      var username = tokenService.verifyAccess(jwtToken).getSubject();
      if (username != null) {
        return Optional.ofNullable((UserDetails) userDetailsService.loadUserByUsername(username));
      }
    }
    return Optional.empty();
  }
  
  public Optional<UserEntity> getAuthenticatedUser() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal() instanceof UserContextDetails) {
      var userContextDetails = (UserDetails) authentication.getPrincipal();
      return Optional.of(userContextDetails.getUser());
    }
    return Optional.empty();
  }

}
