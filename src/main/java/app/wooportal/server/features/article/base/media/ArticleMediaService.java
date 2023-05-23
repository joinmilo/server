package app.wooportal.server.features.article.base.media;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ArticleMediaService
    extends DataService<ArticleMediaEntity, ArticleMediaPredicateBuilder> {

  public ArticleMediaService(DataRepository<ArticleMediaEntity> repo,
      ArticleMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
