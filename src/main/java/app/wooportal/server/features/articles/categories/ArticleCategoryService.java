package app.wooportal.server.features.articles.categories;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ArticleCategoryService
    extends DataService<ArticleCategoryEntity, ArticleCategoryPredicateBuilder> {

  public ArticleCategoryService(DataRepository<ArticleCategoryEntity> repo,
      ArticleCategoryPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
