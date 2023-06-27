package app.wooportal.server.features.article.base;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.features.article.rating.ArticleRatingEntity;
import app.wooportal.server.features.calculateRating.RatingDto;
import app.wooportal.server.features.calculateRating.RatingService;
import app.wooportal.server.features.event.base.EventEntity;
import app.wooportal.server.features.event.rating.EventRatingEntity;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ArticleApi extends CrudApi<ArticleEntity, ArticleService> {

  private final RatingService ratingService;

  public ArticleApi(ArticleService userService, RatingService ratingService) {
    super(userService);
    
    this.ratingService = ratingService;
  }
  
  @Override
  @GraphQLQuery(name = "getArticles")
  public PageableList<ArticleEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getArticle")
  public Optional<ArticleEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ArticleEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveArticles")
  @AdminPermission
  public List<ArticleEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ArticleEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveArticle")
  public ArticleEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ArticleEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteArticles")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteArticle")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLQuery(name = "calculatedRatings")
  public CompletableFuture<RatingDto> calculateAverageRating(
      @GraphQLContext ArticleEntity article) {
    int[] scoresArray = article.getRatings().stream()
        .mapToInt(ArticleRatingEntity::getScore).toArray();
    return ratingService.calculateRating(scoresArray);
  }
}
