package app.wooportal.server.features.contest.comment;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ContestCommentPredicateBuilder
    extends PredicateBuilder<QContestCommentEntity, ContestCommentEntity> {

  public ContestCommentPredicateBuilder() {
    super(QContestCommentEntity.contestCommentEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
  
  public BooleanExpression withContestId(String contestId) {
    return query.contest.id.eq(contestId);
  }
}
