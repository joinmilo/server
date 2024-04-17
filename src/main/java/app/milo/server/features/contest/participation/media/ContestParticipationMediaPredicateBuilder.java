package app.milo.server.features.contest.participation.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class ContestParticipationMediaPredicateBuilder
    extends PredicateBuilder<QContestParticipationMediaEntity, ContestParticipationMediaEntity> {

  public ContestParticipationMediaPredicateBuilder() {
    super(QContestParticipationMediaEntity.contestParticipationMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.media.name.likeIgnoreCase(term);
  }
}
