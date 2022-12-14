package app.wooportal.server.features.deals.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class DealService extends DataService<DealEntity, DealPredicateBuilder> {

  public DealService(DataRepository<DealEntity> repo, DealPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
