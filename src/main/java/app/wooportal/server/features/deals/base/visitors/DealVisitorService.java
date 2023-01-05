package app.wooportal.server.features.deals.base.visitors;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class DealVisitorService extends DataService<DealVisitorEntity, DealVisitorPredicateBuilder> {

  public DealVisitorService(DataRepository<DealVisitorEntity> repo, DealVisitorPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
