package app.milo.server.core.push.subscription;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class SubscriptionPredicateBuilder
    extends PredicateBuilder<QSubscriptionEntity, SubscriptionEntity> {

  public SubscriptionPredicateBuilder() {
    super(QSubscriptionEntity.subscriptionEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.deviceToken.likeIgnoreCase(term);    
  }

  public BooleanExpression withDeviceToken(String key) {
    return query.deviceToken.equalsIgnoreCase(key);
  }

  public BooleanExpression withUserId(String name) {
    return name != null && !name.isBlank() ? query.user.id.equalsIgnoreCase(name) : null;
  }
}
