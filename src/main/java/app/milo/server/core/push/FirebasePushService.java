package app.milo.server.core.push;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import app.milo.server.core.push.subscription.SubscriptionService;
import app.milo.server.core.security.components.user.UserEntity;


@Service
public class FirebasePushService {

  private final SubscriptionService subscriptionService;

  public FirebasePushService(SubscriptionService subscriptionService, PushConfig config)
      throws IOException {
    this.subscriptionService = subscriptionService;
    initializePushService(config);
  }

  private void initializePushService(PushConfig config) throws IOException {
    var firebaseConfigFile = new ClassPathResource(config.getCredentials());
    
    if (FirebaseApp.getApps().isEmpty()) {
      FirebaseApp.initializeApp(FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(firebaseConfigFile.getInputStream()))
          .build());
    }
  }

  public void sendPush(UserEntity user, MessageDto message) {
    if (user.getSubscriptions() != null) {
      for (var iterator = user.getSubscriptions().iterator(); iterator.hasNext();) {
        var subscription = iterator.next();
        try {
          var messageBuilder =
              Message.builder().setToken(subscription.getDeviceToken()).setNotification(Notification
                  .builder().setTitle(message.getTitle()).setBody(message.getContent()).build());

          if (message.getData() != null) {
            messageBuilder
                .setAndroidConfig(AndroidConfig.builder().putAllData(message.getData()).build())
                .setApnsConfig(ApnsConfig.builder()
                    .setAps(Aps.builder()
                        .putAllCustomData(new HashMap<String, Object>(message.getData())).build())
                    .build())
                .setWebpushConfig(
                    WebpushConfig.builder()
                        .setNotification(
                            WebpushNotification.builder().setData(message.getData()).build())
                        .build());
          }
          FirebaseMessaging.getInstance().sendAsync(messageBuilder.build()).get();

        } catch (InterruptedException | ExecutionException e) {
          e.printStackTrace(System.out);
          iterator.remove();
          subscriptionService.deleteById(subscription.getId());
        }
      }
    }

  }
}
