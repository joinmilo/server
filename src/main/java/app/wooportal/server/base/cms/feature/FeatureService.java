package app.wooportal.server.base.cms.feature;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class FeatureService extends DataService<FeatureEntity, FeaturePredicateBuilder> {

  public FeatureService(DataRepository<FeatureEntity> repo, FeaturePredicateBuilder predicate) {
    super(repo, predicate);
  }

  public Optional<FeatureEntity> getByKey(String code) {
    return repo.findOne(singleQuery(predicate.withCode(code)));
  }
}