package app.wooportal.server.base.cms.feature;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class FeatureService extends DataService<FeatureEntity, FeaturePredicateBuilder> {

  public FeatureService(DataRepository<FeatureEntity> repo, FeaturePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
