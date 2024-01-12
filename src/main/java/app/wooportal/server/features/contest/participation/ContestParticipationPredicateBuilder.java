package app.wooportal.server.features.contest.participation;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ContestParticipationPredicateBuilder
    extends PredicateBuilder<QContestParticipationEntity, ContestParticipationEntity> {

  public ContestParticipationPredicateBuilder() {
    super(QContestParticipationEntity.contestParticipationEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.contest.id.likeIgnoreCase(term);
  }
  
  public BooleanExpression withContest(String contestId) {
    return query.contest.id.eq(contestId);
  }
  
  public BooleanExpression withUserContext(String userContextId) {
    return query.userContext.id.eq(userContextId);
  }
}
