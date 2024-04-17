package app.milo.server.features.survey.questionOption;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class SurveyQuestionOptionPredicateBuilder
    extends PredicateBuilder<QSurveyQuestionOptionEntity, SurveyQuestionOptionEntity> {

  public SurveyQuestionOptionPredicateBuilder() {
    super(QSurveyQuestionOptionEntity.surveyQuestionOptionEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
