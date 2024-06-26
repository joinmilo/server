package app.milo.server.features.organisation.comment;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

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
  
  public BooleanExpression withOrganisationId(String organisationId) {
    return query.organisation.id.eq(organisationId);
  }
}
