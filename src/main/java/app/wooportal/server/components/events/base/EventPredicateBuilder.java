package app.wooportal.server.components.events.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class EventPredicateBuilder extends PredicateBuilder<QEventEntity, EventEntity> {

  public EventPredicateBuilder() {
    super(QEventEntity.eventEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.link.likeIgnoreCase(term);
  }
}
