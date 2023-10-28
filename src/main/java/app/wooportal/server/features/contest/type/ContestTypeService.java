package app.wooportal.server.features.contest.type;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ContestTypeService
    extends DataService<ContestTypeEntity, ContestTypePredicateBuilder> {

  public ContestTypeService(DataRepository<ContestTypeEntity> repo,
      ContestTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
