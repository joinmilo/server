package app.wooportal.server.features.survey.questionOption;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class QuestionOptionPredicateBuilder
    extends PredicateBuilder<QQuestionOptionEntity, QuestionOptionEntity> {

  public QuestionOptionPredicateBuilder() {
    super(QQuestionOptionEntity.questionOptionEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
