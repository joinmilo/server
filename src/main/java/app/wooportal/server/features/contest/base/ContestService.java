package app.wooportal.server.features.contest.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ContestService extends DataService<ContestEntity, ContestPredicateBuilder> {

  public ContestService(DataRepository<ContestEntity> repo, ContestPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
