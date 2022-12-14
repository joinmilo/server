package app.wooportal.server.base.cms.landings;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class LandingService
    extends DataService<LandingEntity, LandingPredicateBuilder> {

  public LandingService(DataRepository<LandingEntity> repo,
      LandingPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
