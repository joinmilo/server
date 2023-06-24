package app.wooportal.server.features.event.comment;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class EventCommentPredicateBuilder
    extends PredicateBuilder<QEventCommentEntity, EventCommentEntity> {

  public EventCommentPredicateBuilder() {
    super(QEventCommentEntity.eventCommentEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.userContext.id.likeIgnoreCase(term)
        .or(query.event.id.likeIgnoreCase(term));
  }
  
  public BooleanExpression withEventId(String eventId) {
    return query.event.id.eq(eventId);
  }
}
