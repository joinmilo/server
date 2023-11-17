package app.wooportal.server.features.event.base;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class EventPredicateBuilder extends PredicateBuilder<QEventEntity, EventEntity> {

  public EventPredicateBuilder() {
    super(QEventEntity.eventEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.videoChatLink.likeIgnoreCase(term).or(query.metaDescription.likeIgnoreCase(term))
        .or(query.slug.likeIgnoreCase(term))
        .or(query.translatables.any().content.likeIgnoreCase(term))
        .or(query.translatables.any().name.likeIgnoreCase(term))
        .or(query.translatables.any().shortDescription.likeIgnoreCase(term))
        .or(query.category.translatables.any().name.likeIgnoreCase(term));
  }

  public BooleanExpression withoutId(String eventId) {
    return query.id.ne(eventId);
  }

  public BooleanExpression withCreator(String creatorId) {
    return query.creator.id.eq(creatorId);
  }

  public BooleanExpression withSponsoredTrue() {
    return query.sponsored.isTrue();
  }
  
  public BooleanExpression withOrganisationMember(String userContextId) {
    return query.organisation.members.any().userContext.id.eq(userContextId);
  }
}
