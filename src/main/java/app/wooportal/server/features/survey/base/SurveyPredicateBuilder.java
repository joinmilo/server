package app.wooportal.server.features.survey.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class SurveyPredicateBuilder extends PredicateBuilder<QSurveyEntity, SurveyEntity> {

  public SurveyPredicateBuilder() {
    super(QSurveyEntity.surveyEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.metaDescription.likeIgnoreCase(term)
        .or(query.slug.likeIgnoreCase(term));
  }
}
