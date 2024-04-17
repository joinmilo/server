package app.milo.server.features.survey.answer;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class SurveyAnswerPredicateBuilder extends PredicateBuilder<QSurveyAnswerEntity, SurveyAnswerEntity> {

  public SurveyAnswerPredicateBuilder() {
    super(QSurveyAnswerEntity.surveyAnswerEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
