package app.wooportal.server.features.survey.assignment;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class AssignmentPredicateBuilder
    extends PredicateBuilder<QAssignmentEntity, AssignmentEntity> {

  public AssignmentPredicateBuilder() {
    super(QAssignmentEntity.assignmentEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.survey.slug.likeIgnoreCase(term)
        .or(query.survey.seoDescription.likeIgnoreCase(term));
  }
}
