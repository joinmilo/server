package app.wooportal.server.features.event.base;

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
import app.wooportal.server.features.event.rating.EventRatingEntity;
import app.wooportal.server.features.event.rating.EventRatingService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class EventAnalyticsApi {
  
  private final RatingService<EventEntity, EventRatingEntity> ratingAnalyticsService;
  private final EventRatingService ratingService;
  private final SearchConsoleService searchConsoleService;
  private final VisitableAnalyticsService<EventEntity, ?> visitableAnalyticsService;

  public EventAnalyticsApi(
      RatingService<EventEntity, EventRatingEntity> ratingAnalyticsService,
      EventRatingService ratingService,
      SearchConsoleService searchConsoleService,
      VisitableAnalyticsService<EventEntity, ?> visitableAnalyticsService) {
    this.ratingAnalyticsService = ratingAnalyticsService;
    this.ratingService = ratingService;
    this.searchConsoleService = searchConsoleService;
    this.visitableAnalyticsService = visitableAnalyticsService;
  }
  
  @GraphQLQuery(name = "ratingDistribution")
  public CompletableFuture<AnalyticsDto> ratingDistribution(
      @GraphQLContext EventEntity entity,
      OffsetDateTime startDate,
      OffsetDateTime endDate) {
    return ratingAnalyticsService.calculateRatingDistribution(
        ratingService.getAllBetween(entity, startDate, endDate));
  }
  
  @GraphQLQuery(name = "ratingStatistics")
  public CompletableFuture<Set<AnalyticsDto>> ratingStatistics(
      @GraphQLContext EventEntity entity,
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval) {
    return ratingAnalyticsService.calculateRatingStatistics(
        ratingService.getAllBetween(entity, startDate, endDate),
        startDate, endDate, interval);
  }
  
  @GraphQLQuery(name = "searchStatistics")
  public CompletableFuture<Set<AnalyticsDto>> searchStatistics(
      @GraphQLContext EventEntity entity,
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval) throws IOException {
    return searchConsoleService.getEntitySearchStatistics(
        startDate, endDate, interval, entity);
  }
  
  @GraphQLQuery(name = "visitorStatistics")
  public CompletableFuture<Set<AnalyticsDto>> visitorStatistics(
      @GraphQLContext EventEntity entity,
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval) throws Throwable {
    return visitableAnalyticsService.getEntityVisitableStatistics(
        startDate, endDate, interval, entity);
  }
}
