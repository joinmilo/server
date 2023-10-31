package app.wooportal.server.features.organisation.base;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class OrganisationPredicateBuilder extends PredicateBuilder<QOrganisationEntity, OrganisationEntity> {

  public OrganisationPredicateBuilder() {
    super(QOrganisationEntity.organisationEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term).or(query.slug.likeIgnoreCase(term))
        .or(query.metaDescription.likeIgnoreCase(term));
  }

  public BooleanExpression withoutId(String organisationId) {
    return query.id.ne(organisationId);
  }

  public BooleanExpression withMemberUser(String userId) {
    return query.members.any().userContext.id.eq(userId);
  }
}
