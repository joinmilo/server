package app.wooportal.server.features.articles.articleVisitors;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ArticleVisitorService
    extends DataService<ArticleVisitorEntity, ArticleVisitorPredicateBuilder> {

  public ArticleVisitorService(DataRepository<ArticleVisitorEntity> repo,
      ArticleVisitorPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
