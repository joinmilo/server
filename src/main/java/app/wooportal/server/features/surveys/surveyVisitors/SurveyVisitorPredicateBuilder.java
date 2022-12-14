package app.wooportal.server.features.surveys.surveyVisitors;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class SurveyVisitorPredicateBuilder
    extends PredicateBuilder<QSurveyVisitorEntity, SurveyVisitorEntity> {

  public SurveyVisitorPredicateBuilder() {
    super(QSurveyVisitorEntity.surveyVisitorEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.parent.slug.likeIgnoreCase(term)
        .or(query.parent.seoDescription.likeIgnoreCase(term));
  }
}
