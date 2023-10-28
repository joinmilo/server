package app.wooportal.server.base.thirdparty.app;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AppService extends DataService<AppEntity, AppPredicateBuilder> {

  public AppService(DataRepository<AppEntity> repo, AppPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
