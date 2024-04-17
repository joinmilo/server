package app.milo.server.features.survey.question;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class SurveyQuestionPredicateBuilder extends PredicateBuilder<QSurveyQuestionEntity, SurveyQuestionEntity> {

  public SurveyQuestionPredicateBuilder() {
    super(QSurveyQuestionEntity.surveyQuestionEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
