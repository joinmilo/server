package app.wooportal.server.base.feedback.type;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class FeedbackTypePredicateBuilder
    extends PredicateBuilder<QFeedbackTypeEntity, FeedbackTypeEntity> {

  public FeedbackTypePredicateBuilder() {
    super(QFeedbackTypeEntity.feedbackTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
