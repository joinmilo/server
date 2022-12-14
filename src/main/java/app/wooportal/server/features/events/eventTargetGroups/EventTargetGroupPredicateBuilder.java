package app.wooportal.server.features.events.eventTargetGroups;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class EventTargetGroupPredicateBuilder
    extends PredicateBuilder<QEventTargetGroupEntity, EventTargetGroupEntity> {

  public EventTargetGroupPredicateBuilder() {
    super(QEventTargetGroupEntity.eventTargetGroupEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
