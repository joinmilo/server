package app.wooportal.server.base.thirdparty.appPlatform;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AppPlatformService extends DataService<AppPlatformEntity, AppPlatformPredicateBuilder> {

  public AppPlatformService(DataRepository<AppPlatformEntity> repo, AppPlatformPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
