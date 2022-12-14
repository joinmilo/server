package app.wooportal.server.features.events.eventRatings;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class EventRatingPredicateBuilder
    extends PredicateBuilder<QEventRatingEntity, EventRatingEntity> {

  public EventRatingPredicateBuilder() {
    super(QEventRatingEntity.eventRatingEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
