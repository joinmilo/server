package app.wooportal.server.features.organisations.ratings;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class OrganisationRatingPredicateBuilder
    extends PredicateBuilder<QOrganisationRatingEntity, OrganisationRatingEntity> {

  public OrganisationRatingPredicateBuilder() {
    super(QOrganisationRatingEntity.organisationRatingEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
