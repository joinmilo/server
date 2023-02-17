package app.wooportal.server.features.articles.comments;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ArticleCommentService
    extends DataService<ArticleCommentEntity, ArticleCommentPredicateBuilder> {

  public ArticleCommentService(DataRepository<ArticleCommentEntity> repo,
      ArticleCommentPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
