package app.milo.server.base.thirdparty.app;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class AppService extends DataService<AppEntity, AppPredicateBuilder> {

  public AppService(DataRepository<AppEntity> repo, AppPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
