package app.wooportal.server.features.survey.result;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class SurveyResultPredicateBuilder
    extends PredicateBuilder<QSurveyResultEntity, SurveyResultEntity> {

  public SurveyResultPredicateBuilder() {
    super(QSurveyResultEntity.surveyResultEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
