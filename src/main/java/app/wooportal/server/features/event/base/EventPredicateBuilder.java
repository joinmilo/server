package app.wooportal.server.features.event.base;

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
    return query.videoChatLink.likeIgnoreCase(term).or(query.seoDescription.likeIgnoreCase(term))
        .or(query.slug.likeIgnoreCase(term));
  }
}
