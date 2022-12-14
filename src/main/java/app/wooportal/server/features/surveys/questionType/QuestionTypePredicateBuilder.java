package app.wooportal.server.features.surveys.questionType;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class QuestionTypePredicateBuilder
    extends PredicateBuilder<QQuestionTypeEntity, QuestionTypeEntity> {

  public QuestionTypePredicateBuilder() {
    super(QQuestionTypeEntity.questionTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
