package app.wooportal.server.core.security.services;

import java.util.Collections;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.security.JwtUserDetails;
import app.wooportal.server.core.security.components.token.TokenService;
import app.wooportal.server.core.security.components.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class AuthenticationService {
  
  private final TokenService tokenService;

  private final JwtUserDetailsService userDetailsService;
  
  private final AuthenticationManager authManager;

  @Lazy
  public AuthenticationService(
      AuthenticationManager authManager,
      TokenService tokenService,
      JwtUserDetailsService userDetailsService) {
    this.authManager = authManager;
    this.tokenService = tokenService;
    this.userDetailsService = userDetailsService;
  }
  
  public Optional<JwtUserDetails> authenticateCurrentUser(String password) {
    var currentUser = getAuthenticatedUser();
    return currentUser.isPresent()
        ? authenticate(currentUser.get().getEmail(), password)
        : Optional.empty();
  }

  public Optional<JwtUserDetails> authenticate(String username, String password) {
    return Optional.of((JwtUserDetails) authManager
        .authenticate(
            new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()))
        .getPrincipal());
  }

  public Optional<UserEntity> getAuthenticatedUser() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return Optional.of(jwtUserDetails.getUser());
    }
    return Optional.empty();
  }
  
  public Optional<UserEntity> getUserFromToken(String jwtToken) {
    var userDetails = retrieveUserDetailsFromToken(jwtToken);
    return userDetails.isPresent()
      ? Optional.ofNullable(userDetails.get().getUser())
      : Optional.empty();
  }
  
  public Optional<UsernamePasswordAuthenticationToken> getAuthenticationToken(HttpServletRequest request) {
    if (request != null) {
      try {
        var jwtToken = request.getHeader("Authorization");
        var userDetails = retrieveUserDetailsFromToken(jwtToken);
        if (userDetails.isPresent()) {
          return Optional.of(new UsernamePasswordAuthenticationToken(
              userDetails.get(), null, Collections.emptyList())); 
        }
      } catch (Exception ignored) { }
    }
    return Optional.empty();
  }
  
  private Optional<JwtUserDetails> retrieveUserDetailsFromToken(String jwtToken) {
    if (jwtToken != null && !jwtToken.contains("undefined")) {
      var username = tokenService.verifyAccess(jwtToken).getSubject();
      if (username != null) {
        return Optional.ofNullable(userDetailsService.loadUserByUsername(username));
      }
    }
    return Optional.empty();
  }

}
