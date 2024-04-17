package app.milo.server.features.article.components.category;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class ArticleCategoryService
    extends DataService<ArticleCategoryEntity, ArticleCategoryPredicateBuilder> {

  public ArticleCategoryService(DataRepository<ArticleCategoryEntity> repo,
      ArticleCategoryPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
