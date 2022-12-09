package app.wooportal.server.core.push.subscriptionType;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class SubscriptionTypePredicateBuilder
    extends PredicateBuilder<QSubscriptionTypeEntity, SubscriptionTypeEntity> {

  public SubscriptionTypePredicateBuilder() {
    super(QSubscriptionTypeEntity.subscriptionTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term).or(query.description.likeIgnoreCase(term));
  }

  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }
}
