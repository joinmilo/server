package app.wooportal.server.features.events.eventVisitors;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class EventVisitorPredicateBuilder
    extends PredicateBuilder<QEventVisitorEntity, EventVisitorEntity> {

  public EventVisitorPredicateBuilder() {
    super(QEventVisitorEntity.eventVisitorEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
