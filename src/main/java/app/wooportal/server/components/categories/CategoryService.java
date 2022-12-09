package app.wooportal.server.components.categories;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class CategoryService extends DataService<CategoryEntity, CategoryPredicateBuilder> {

  public CategoryService(DataRepository<CategoryEntity> repo, CategoryPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
