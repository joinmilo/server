package app.milo.server.features.contest.type;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class ContestTypeService
    extends DataService<ContestTypeEntity, ContestTypePredicateBuilder> {

  public ContestTypeService(DataRepository<ContestTypeEntity> repo,
      ContestTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
