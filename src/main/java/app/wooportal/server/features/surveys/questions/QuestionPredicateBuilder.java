package app.wooportal.server.features.surveys.questions;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class QuestionPredicateBuilder extends PredicateBuilder<QQuestionEntity, QuestionEntity> {

  public QuestionPredicateBuilder() {
    super(QQuestionEntity.questionEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
