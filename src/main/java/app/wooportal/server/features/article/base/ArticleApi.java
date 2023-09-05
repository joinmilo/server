package app.wooportal.server.features.article.base;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import app.wooportal.server.base.analytics.googleSearch.SearchAnalyticsDto;
import app.wooportal.server.base.analytics.googleSearch.SearchConsoleService;
import app.wooportal.server.base.analytics.googleSearch.SearchDimension;
import app.wooportal.server.base.rating.RatingDto;
import app.wooportal.server.base.rating.RatingService;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.features.article.comment.ArticleCommentEntity;
import app.wooportal.server.features.article.comment.ArticleCommentService;
import app.wooportal.server.features.event.base.EventEntity;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ArticleApi extends CrudApi<ArticleEntity, ArticleService> {

  private final RatingService ratingService;
  private final ArticleCommentService commentService;
  private final SearchConsoleService searchConsoleService;

  public ArticleApi(ArticleService service,
      RatingService ratingService,
      ArticleCommentService commentService,
      SearchConsoleService searchConsoleService) {
    super(service);
    
    this.ratingService = ratingService;
    this.commentService = commentService;
    this.searchConsoleService = searchConsoleService;
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
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteArticle")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLQuery(name = "lastArticleComment")
  public Optional<ArticleCommentEntity> getLastComment(
      @GraphQLContext ArticleEntity article) {
    return commentService.getMostRecentByArticle(article.getId());
  }
  
  @GraphQLQuery(name = "calculatedRatings")
  public CompletableFuture<RatingDto> calculateAverageRating(
      @GraphQLContext ArticleEntity article) {
    return article.getRatings() != null && !article.getRatings().isEmpty()
        ? ratingService.calculateRating(
            article.getRatings().stream().map(rating -> rating.getScore()).collect(Collectors.toList()))
        : CompletableFuture.completedFuture(new RatingDto());
  }
  
  @GraphQLMutation(name = "sponsorArticle")
  public Boolean sponsorArticle(String articleId) {
    return service.sponsorArticle(articleId);
  }
  
  
  @GraphQLQuery(name = "searchConsoleArticleDetails")
  public List<AnalyticsDto> searchConsoleEventDetails(@GraphQLContext ArticleEntity article,
      LocalDate startDate, LocalDate endDate) throws IOException {

    OffsetDateTime targetDateTime = OffsetDateTime.of(2023, 10, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    var keyword = article.getCreated().isBefore(targetDateTime) ? article.getId() : article.getSlug();
    return searchConsoleService.calculateForFeature(startDate, endDate,
        keyword);
  }

}
