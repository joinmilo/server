package app.wooportal.server.features.article.components.rating;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.base.userContext.base.UserContextService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.article.components.base.ArticleEntity;

@Service
public class ArticleRatingService
    extends DataService<ArticleRatingEntity, ArticleRatingPredicateBuilder> {

  public ArticleRatingService(DataRepository<ArticleRatingEntity> repo,
      ArticleRatingPredicateBuilder predicate,
      UserContextService userContextService) {
    super(repo, predicate);
    
    addService("userContext", userContextService);
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
  
  @Override
  public Optional<ArticleRatingEntity> getExisting(ArticleRatingEntity entity) {
    return entity != null
        && entity.getUserContext() != null
        && entity.getUserContext().getId() != null
        && entity.getParent() != null
        && entity.getParent().getId() != null
       ? repo.findOne(singleQuery(predicate.withUserContext(entity.getUserContext().getId()))
           .and(predicate.withParentId(entity.getParent().getId()))
         )
       : Optional.empty();
  }
  
}
