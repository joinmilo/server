package app.wooportal.server.features.article.rating;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.article.base.ArticleEntity;

@Service
public class ArticleRatingService
    extends DataService<ArticleRatingEntity, ArticleRatingPredicateBuilder> {

  public ArticleRatingService(DataRepository<ArticleRatingEntity> repo,
      ArticleRatingPredicateBuilder predicate) {
    super(repo, predicate);
  }

  public List<ArticleRatingEntity> getAllBetween(
      ArticleEntity parent,
      OffsetDateTime startDate,
      OffsetDateTime endDate) {
    return parent != null
        ? repo.findAll(collectionQuery(predicate.withParentId(parent.getId()))
            .and(predicate.modifiedBetween(startDate, endDate))
          ).getList()
        : List.of();
  }
}
