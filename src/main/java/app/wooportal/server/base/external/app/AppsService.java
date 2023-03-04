package app.wooportal.server.base.external.app;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AppsService extends DataService<AppEntity, AppPredicateBuilder> {

  public AppsService(DataRepository<AppEntity> repo, AppPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
