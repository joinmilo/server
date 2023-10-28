package app.wooportal.server.core.security.services;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import app.wooportal.server.core.security.components.token.TokenDto;

public class ApiAuthenticationToken extends AbstractAuthenticationToken {

  private static final long serialVersionUID = 1L;
  
  private final TokenDto token;
  
  public ApiAuthenticationToken(TokenDto token) {
    super(null);
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
