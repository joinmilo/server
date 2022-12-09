package app.wooportal.server.core.security.services;

import app.wooportal.server.core.security.components.token.TokenDto;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class ApiAuthenticationToken extends AbstractAuthenticationToken {

  private static final long serialVersionUID = 1L;
  
  private final TokenDto token;
  
  public ApiAuthenticationToken(TokenDto token) {
    super (null);
    this.token = token;
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return token;
  }

}
