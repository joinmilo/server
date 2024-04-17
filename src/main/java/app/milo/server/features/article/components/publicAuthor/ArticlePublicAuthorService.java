package app.milo.server.features.article.components.publicAuthor;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class ArticlePublicAuthorService extends DataService<ArticlePublicAuthorEntity, ArticlePublicAuthorPredicateBuilder> {

  public ArticlePublicAuthorService(DataRepository<ArticlePublicAuthorEntity> repo, ArticlePublicAuthorPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
