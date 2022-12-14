package app.wooportal.server.base.cms.landingFeatures;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class LandingFeatureService
    extends DataService<LandingFeatureEntity, LandingFeaturePredicateBuilder> {

  public LandingFeatureService(DataRepository<LandingFeatureEntity> repo,
      LandingFeaturePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
