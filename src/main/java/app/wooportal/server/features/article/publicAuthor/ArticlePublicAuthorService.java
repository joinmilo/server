package app.wooportal.server.features.article.publicAuthor;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ArticlePublicAuthorService extends DataService<ArticlePublicAuthorEntity, ArticlePublicAuthorPredicateBuilder> {

  public ArticlePublicAuthorService(DataRepository<ArticlePublicAuthorEntity> repo, ArticlePublicAuthorPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
