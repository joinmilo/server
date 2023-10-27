package app.wooportal.server.core.push;

import org.springframework.stereotype.Component;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.core.security.services.AuthenticationService;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class PushApi {

  private final AuthenticationService authService;
  private final PushService pushService;
  private final GraphQlPushService graphQlPushService;
  private final UserService userService;

  public PushApi(
      AuthenticationService authService,
      PushService pushService,
      GraphQlPushService graphQlPushService,
      UserService userService) {
    
    this.authService = authService;
    this.pushService = pushService;
    this.graphQlPushService = graphQlPushService;
    this.userService = userService;
  }

//  @GraphQLMutation(name = "sendGlobalPush")
//  public Boolean sendGlobalPush(MessageDto message) {
//    pushService.sendPush(userService.getRepo().findAll(), message);
//    return true;
//  }
  
//  @GraphQLSubscription
//  public Publisher<MessageDto> addListener(String token) {
//    var user =  authService.getUserFromToken(token);
//    
//    if (user.isEmpty()) {
//      throw new InvalidTokenException("Invalid token, either user doesn't exist or token invalid", token);
//    }
//    return graphQlPushService.addListener(user);
//  }
//  
//  @GraphQLSubscription
//  public Publisher<MessageDto> addChatListener(String token) {
//    var user =  authService.getUserFromToken(token);
//    
//    if (user.isEmpty()) {
//      throw new InvalidTokenException("Invalid token, either user doesn't exist or token invalid", token);
//    }
//    return graphQlPushService.addChatListener(user);
//  }
}


