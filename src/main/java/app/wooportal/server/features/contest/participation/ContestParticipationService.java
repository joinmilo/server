package app.wooportal.server.features.contest.participation;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ContestParticipationService
    extends DataService<ContestParticipationEntity, ContestParticipationPredicateBuilder> {

  public ContestParticipationService(DataRepository<ContestParticipationEntity> repo,
      ContestParticipationPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
