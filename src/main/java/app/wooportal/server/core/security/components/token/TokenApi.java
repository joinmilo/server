package app.wooportal.server.core.security.components.token;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.security.services.AuthenticationService;
import app.wooportal.server.core.security.services.JwtUserDetailsService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Component
@GraphQLApi
public class TokenApi {
  
  private final AuthenticationService authService;

  private final TokenService tokenService;

  private final JwtUserDetailsService userDetailService;

  public TokenApi(AuthenticationService authService, TokenService tokenService,
      JwtUserDetailsService userDetailService) {

    this.authService = authService;
    this.tokenService = tokenService;
    this.userDetailService = userDetailService;
  }

  @GraphQLMutation(name = "createToken")
  public TokenDto createToken(String username, String password) {
    try {
      var jwtUserDetails = authService.authenticate(username, password);

      if (jwtUserDetails.isPresent()) {
        return new TokenDto(
            tokenService.createAccessToken(jwtUserDetails.get()),
            tokenService.createRefreshToken(jwtUserDetails.get()));
      }
    } catch (Exception ignored) { }
    throw new BadCredentialsException(username);
  }

  @GraphQLMutation(name = "refreshToken")
  public TokenDto refreshToken(String refreshToken) {
    var decodedToken = tokenService.verifyRefresh(refreshToken);
    return new TokenDto(
        tokenService.createAccessToken(userDetailService.loadUserByUsername(
            decodedToken.getSubject())),
        refreshToken);
  }

}
