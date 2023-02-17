package app.wooportal.server.features.contests.participations;

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
    return null;
  }
}
