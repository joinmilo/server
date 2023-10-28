package app.wooportal.server.features.contest.vote;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

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
}
