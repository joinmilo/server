package app.wooportal.server.features.deal.category;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class DealCategoryService extends DataService<DealCategoryEntity, DealCategoryPredicateBuilder> {

  public DealCategoryService(DataRepository<DealCategoryEntity> repo, DealCategoryPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
