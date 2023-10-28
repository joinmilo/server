package app.wooportal.server.features.organisation.media;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class OrganisationMediaPredicateBuilder
		extends PredicateBuilder<QOrganisationMediaEntity, OrganisationMediaEntity> {

	public OrganisationMediaPredicateBuilder() {
		super(QOrganisationMediaEntity.organisationMediaEntity);
	}

	@Override
	public BooleanExpression freeSearch(String term) {
		return query.media.name.likeIgnoreCase(term);
	}

	public BooleanExpression withMedia(String mediaId) {
		return mediaId != null ? query.media.id.eq(mediaId) : null;
	}

	public BooleanExpression withOrganisation(String organisationId) {
		return query.organisation.id.eq(organisationId);
	}

	public BooleanExpression withMember(String creatorId) {
		return query.organisation.members.any().id.eq(creatorId);
	}
}
