package app.wooportal.server.features.article.components.base;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import app.wooportal.server.base.analytics.rating.RatingService;
import app.wooportal.server.base.analytics.search.SearchConsoleService;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;
import app.wooportal.server.core.base.dto.analytics.IntervalFilter;
import app.wooportal.server.core.visit.visitable.VisitableAnalyticsService;
import app.wooportal.server.features.article.components.rating.ArticleRatingEntity;
import app.wooportal.server.features.article.components.rating.ArticleRatingService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ArticleAnalyticsApi {

  private final RatingService<ArticleEntity, ArticleRatingEntity> ratingAnalyticsService;
  private final ArticleRatingService ratingService;
  private final SearchConsoleService searchConsoleService;
  private final VisitableAnalyticsService<ArticleEntity, ?>  visitableAnalyticsService;

  public ArticleAnalyticsApi(
      RatingService<ArticleEntity, ArticleRatingEntity> ratingAnalyticsService,
      ArticleRatingService ratingService,
      SearchConsoleService searchConsoleService,
      VisitableAnalyticsService<ArticleEntity, ?> visitableAnalyticsService) {
    this.ratingAnalyticsService = ratingAnalyticsService;
    this.ratingService = ratingService;
    this.searchConsoleService = searchConsoleService;
    this.visitableAnalyticsService = visitableAnalyticsService;
  }
  
  @GraphQLQuery(name = "ratingDistribution")
  public CompletableFuture<AnalyticsDto> ratingDistribution(
      @GraphQLContext ArticleEntity entity,
      OffsetDateTime startDate,
      OffsetDateTime endDate) {
    return ratingAnalyticsService.calculateRatingDistribution(
        ratingService.getAllBetween(entity, startDate, endDate));
  }
  
  @GraphQLQuery(name = "ratingStatistics")
  public CompletableFuture<Set<AnalyticsDto>> ratingStatistics(
      @GraphQLContext ArticleEntity entity,
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval) {
    return ratingAnalyticsService.calculateRatingStatistics(
        ratingService.getAllBetween(entity, startDate, endDate),
        startDate, endDate, interval);
  }
  
  @GraphQLQuery(name = "searchStatistics")
  public CompletableFuture<Set<AnalyticsDto>> searchStatistics(
      @GraphQLContext ArticleEntity article,
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval) throws IOException {
    return searchConsoleService.getEntitySearchStatistics(
        startDate,
        endDate,
        interval,
        article);
  }
  
  @GraphQLQuery(name = "visitorStatistics")
  public CompletableFuture<Set<AnalyticsDto>> visitorStatistics(
      @GraphQLContext ArticleEntity article,
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval) throws Throwable {
    return visitableAnalyticsService.getEntityVisitableStatistics(
        startDate, endDate, interval, article);
  }

}
