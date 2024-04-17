package app.milo.server.features.contest.vote;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class ContestVotePredicateBuilder
    extends PredicateBuilder<QContestVoteEntity, ContestVoteEntity> {

  public ContestVotePredicateBuilder() {
    super(QContestVoteEntity.contestVoteEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
  
  public BooleanExpression withContest(String contestId) {
    return query.contestParticipation.contest.id.eq(contestId);
  }
  
  public BooleanExpression withUserContext(String userContextId) {
    return query.userContext.id.eq(userContextId);
  }
}
