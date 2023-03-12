package app.wooportal.server.base.feedback.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class FeedbackPredicateBuilder extends PredicateBuilder<QFeedbackEntity, FeedbackEntity> {

  public FeedbackPredicateBuilder() {
    super(QFeedbackEntity.feedbackEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.type.id.likeIgnoreCase(term);
  }
}
