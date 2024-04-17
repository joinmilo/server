package app.milo.server.core.push;

import java.util.Optional;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import app.milo.server.core.security.components.user.UserEntity;
import io.leangen.graphql.spqr.spring.util.ConcurrentMultiMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Service
public class GraphQlPushService {

  private final ConcurrentMultiMap<String, FluxSink<MessageDto>> subscribers = new ConcurrentMultiMap<>();
  
  private final ConcurrentMultiMap<String, FluxSink<MessageDto>> chatSubscribers = new ConcurrentMultiMap<>();

  public Publisher<MessageDto> addListener(Optional<UserEntity> user) {
    return Flux.create(
        subscriber -> subscribers.add(
          user.get().getId(), 
          subscriber.onDispose(() -> subscribers.remove(user.get().getId(), subscriber))), 
        FluxSink.OverflowStrategy.LATEST);
  }

  public Publisher<MessageDto> addChatListener(Optional<UserEntity> user) {
    return Flux.create(
        subscriber -> chatSubscribers.add(
          user.get().getId(), 
          subscriber.onDispose(() -> chatSubscribers.remove(user.get().getId(), subscriber))), 
        FluxSink.OverflowStrategy.LATEST);
  }
  
  public void sendPush(UserEntity user, MessageDto message) {
    switch(message.getType()) {
      case chat:
        chatSubscribers.get(user.getId()).forEach(subscriber -> subscriber.next(message));
        break;
      case deletedUser:
      case evaluation:
      case event:
      case global:
      case jobAd:
      default:
        subscribers.get(user.getId()).forEach(subscriber -> subscriber.next(message));
    }
  }
}
