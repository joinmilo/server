package app.milo.server.features.deal.components.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class DealPredicateBuilder extends PredicateBuilder<QDealEntity, DealEntity> {

	public DealPredicateBuilder() {
		super(QDealEntity.dealEntity);
	}

	@Override
	public BooleanExpression freeSearch(String term) {
		return query.metaDescription.likeIgnoreCase(term).or(query.slug.likeIgnoreCase(term))
				.or(query.contact.name.likeIgnoreCase(term)).or(query.contact.email.likeIgnoreCase(term))
				.or(query.translatables.any().shortDescription.likeIgnoreCase(term))
				.or(query.translatables.any().name.likeIgnoreCase(term));
	}

	public BooleanExpression withoutId(String dealId) {
		return query.id.ne(dealId);
	}

	public BooleanExpression withCreator(String creatorId) {
		return query.creator.id.eq(creatorId);
	}

	public BooleanExpression withSponsoredTrue() {
		return query.sponsored.isTrue();
	}
}
