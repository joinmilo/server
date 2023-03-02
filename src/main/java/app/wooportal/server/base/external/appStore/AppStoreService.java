package app.wooportal.server.base.external.appStore;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AppStoreService extends DataService<AppStoreEntity, AppStorePredicateBuilder> {

  public AppStoreService(DataRepository<AppStoreEntity> repo, AppStorePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
