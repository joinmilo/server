package app.wooportal.server.core.security.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.security.JwtUserDetails;

@Service
public class AuthorizationService {
  
  public boolean isAdmin(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isAdmin();
    }
    return false;
  }

  public boolean isApproved(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isVerified();
    }
    return false;
  }

  public boolean isVerified(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isVerified();
    }
    return false;
  }

}
