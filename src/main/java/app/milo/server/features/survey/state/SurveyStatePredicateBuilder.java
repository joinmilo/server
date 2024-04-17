package app.milo.server.features.survey.state;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class SurveyStatePredicateBuilder
    extends PredicateBuilder<QSurveyStateEntity, SurveyStateEntity> {

  public SurveyStatePredicateBuilder() {
    super(QSurveyStateEntity.surveyStateEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return  query.code.likeIgnoreCase(term);
  }
}
