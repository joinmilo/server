package app.wooportal.server.features.article.comment;

import java.util.Optional;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.utils.SortPageUtils;

@Service
public class ArticleCommentService
    extends DataService<ArticleCommentEntity, ArticleCommentPredicateBuilder> {

  public ArticleCommentService(DataRepository<ArticleCommentEntity> repo,
      ArticleCommentPredicateBuilder predicate) {
    super(repo, predicate);
  }
  
  public Optional<ArticleCommentEntity> getMostRecentByArticle(String articleId) {
    var result = repo.findAll(
        collectionQuery(predicate.withArticleId(articleId))
          .setLimit(1)
          .setSort(SortPageUtils.createSort(Direction.DESC, "modified")));
    
    return result != null && !result.isEmpty()
        ? Optional.ofNullable(result.get(0))
        : Optional.empty();
  }
}
