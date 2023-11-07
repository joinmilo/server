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
  
  public BooleanExpression withoutId(String surveyId) {
    return query.id.ne(surveyId);
  }

  public BooleanExpression withSponsoredTrue() {
    return query.sponsored.isTrue();
  }
}
