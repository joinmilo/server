package app.wooportal.server.features.organisations.organisationVisitor;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class OrganisationVisitorPredicateBuilder
    extends PredicateBuilder<QOrganisationVisitorEntity, OrganisationVisitorEntity> {

  public OrganisationVisitorPredicateBuilder() {
    super(QOrganisationVisitorEntity.organisationVisitorEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
