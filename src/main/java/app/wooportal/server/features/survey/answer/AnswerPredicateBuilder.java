package app.wooportal.server.features.survey.answer;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class AnswerPredicateBuilder extends PredicateBuilder<QAnswerEntity, AnswerEntity> {

  public AnswerPredicateBuilder() {
    super(QAnswerEntity.answerEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
