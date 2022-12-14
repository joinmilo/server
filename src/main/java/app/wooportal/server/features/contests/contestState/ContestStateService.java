package app.wooportal.server.features.contests.contestState;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ContestStateService
    extends DataService<ContestStateEntity, ContestStatePredicateBuilder> {

  public ContestStateService(DataRepository<ContestStateEntity> repo,
      ContestStatePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
