package app.wooportal.server.features.organisation.member;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class OrganisationMemberPredicateBuilder extends PredicateBuilder<QOrganisationMemberEntity, OrganisationMemberEntity> {

  public OrganisationMemberPredicateBuilder() {
    super(QOrganisationMemberEntity.organisationMemberEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
