package app.milo.server.features.organisation.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

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
	public BooleanExpression withSponsoredTrue() {
		return query.sponsored.isTrue();
	}

  public BooleanExpression withApprovedOrga(String organisationId) {
    return query.approved.isTrue().and(query.id.eq(organisationId));
  }
}
