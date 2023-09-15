package app.wooportal.server.features.event.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class EventMediaPredicateBuilder
    extends PredicateBuilder<QEventMediaEntity, EventMediaEntity> {

  public EventMediaPredicateBuilder() {
    super(QEventMediaEntity.eventMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.media.name.likeIgnoreCase(term);
  }
}
