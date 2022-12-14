package app.wooportal.server.features.organisations.organisationComments;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class OrganisationCommentPredicateBuilder
    extends PredicateBuilder<QOrganisationCommentEntity, OrganisationCommentEntity> {

  public OrganisationCommentPredicateBuilder() {
    super(QOrganisationCommentEntity.organisationCommentEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
