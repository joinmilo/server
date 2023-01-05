package app.wooportal.server.base.developers;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class DeveloperService extends DataService<DeveloperEntity, DeveloperPredicateBuilder> {

  public DeveloperService(DataRepository<DeveloperEntity> repo,
      DeveloperPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
