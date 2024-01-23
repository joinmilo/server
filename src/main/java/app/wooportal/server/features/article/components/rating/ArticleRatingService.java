package app.wooportal.server.features.article.components.rating;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.base.userContext.base.UserContextService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.config.GeneralConfiguration;
import app.wooportal.server.core.messaging.MailService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.article.components.base.ArticleEntity;
import app.wooportal.server.features.article.components.base.ArticleService;

@Service
public class ArticleRatingService
    extends DataService<ArticleRatingEntity, ArticleRatingPredicateBuilder> {
  
  private final UserContextService userContextService;
  private final MailService mailService;
  private final GeneralConfiguration config;
  private final ArticleService articleService;

  public ArticleRatingService(DataRepository<ArticleRatingEntity> repo,
      ArticleRatingPredicateBuilder predicate,
      UserContextService userContextService,
      MailService mailService,
      GeneralConfiguration config,
      ArticleService articleService) {
    super(repo, predicate);
    
    this.userContextService = userContextService;
    this.mailService = mailService;
    this.articleService = articleService;
    this.config = config;
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
  
  private String createArticleDetailsLink(String slug) {
    return config.getHost() + "/portal/articles/" + slug;
  }
}
