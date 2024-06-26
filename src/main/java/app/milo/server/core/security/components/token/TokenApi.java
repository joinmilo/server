package app.milo.server.core.security.components.token;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import app.milo.server.base.userContext.security.UserContextDetailsService;
import app.milo.server.core.error.exception.VerificationPendingException;
import app.milo.server.core.security.services.AuthenticationService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Component
@GraphQLApi
public class TokenApi {
  
  private final AuthenticationService authService;

  private final TokenService tokenService;

  private final UserContextDetailsService userDetailService;

  public TokenApi(AuthenticationService authService,
      TokenService tokenService,
      UserContextDetailsService userDetailService) {

    this.authService = authService;
    this.tokenService = tokenService;
    this.userDetailService = userDetailService;
  }

  @GraphQLMutation(name = "createToken")
  public TokenDto createToken(String email, String password) {
    var jwtUserDetails = authService.authenticate(email, password);

    if (jwtUserDetails.isPresent()) {
      
      if (!jwtUserDetails.get().isVerified()) {
        throw new VerificationPendingException("mail not verified");
      }

      return new TokenDto(
          tokenService.createAccessToken(jwtUserDetails.get()),
          tokenService.createRefreshToken(jwtUserDetails.get()));
    }
    throw new BadCredentialsException(email);
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
