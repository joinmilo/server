package app.wooportal.server.features.articles.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ArticleService extends DataService<ArticleEntity, ArticlePredicateBuilder> {

  public ArticleService(DataRepository<ArticleEntity> repo, ArticlePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
