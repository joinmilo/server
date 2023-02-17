package app.wooportal.server.features.articles.ratings;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ArticleRatingService
    extends DataService<ArticleRatingEntity, ArticleRatingPredicateBuilder> {

  public ArticleRatingService(DataRepository<ArticleRatingEntity> repo,
      ArticleRatingPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
