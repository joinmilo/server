package app.milo.server.features.deal.components.category;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class DealCategoryService extends DataService<DealCategoryEntity, DealCategoryPredicateBuilder> {

  public DealCategoryService(DataRepository<DealCategoryEntity> repo, DealCategoryPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
