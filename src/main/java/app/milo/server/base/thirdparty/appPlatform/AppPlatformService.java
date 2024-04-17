package app.milo.server.base.thirdparty.appPlatform;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class AppPlatformService extends DataService<AppPlatformEntity, AppPlatformPredicateBuilder> {

  public AppPlatformService(DataRepository<AppPlatformEntity> repo, AppPlatformPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
