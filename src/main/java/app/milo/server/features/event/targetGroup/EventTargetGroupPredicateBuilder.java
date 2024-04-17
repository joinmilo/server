package app.milo.server.features.event.targetGroup;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

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
