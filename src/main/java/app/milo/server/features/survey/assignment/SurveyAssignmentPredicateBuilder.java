package app.milo.server.features.survey.assignment;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class SurveyAssignmentPredicateBuilder
    extends PredicateBuilder<QSurveyAssignmentEntity, SurveyAssignmentEntity> {

  public SurveyAssignmentPredicateBuilder() {
    super(QSurveyAssignmentEntity.surveyAssignmentEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.survey.slug.likeIgnoreCase(term)
        .or(query.survey.metaDescription.likeIgnoreCase(term));
  }
}
