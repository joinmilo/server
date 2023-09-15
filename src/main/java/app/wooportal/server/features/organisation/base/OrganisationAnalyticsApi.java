package app.wooportal.server.features.organisation.base;

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
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class OrganisationAnalyticsApi {
  
  private final RatingService ratingService;
  private final SearchConsoleService searchConsoleService;
  private final VisitableAnalyticsService<OrganisationEntity, ?> visitableAnalyticsService;

  public OrganisationAnalyticsApi(
      RatingService ratingService,
      SearchConsoleService searchConsoleService,
      VisitableAnalyticsService<OrganisationEntity, ?> visitableAnalyticsService) {
    this.ratingService = ratingService;
    this.searchConsoleService = searchConsoleService;
    this.visitableAnalyticsService = visitableAnalyticsService;
  }
  
  @GraphQLQuery(name = "ratingDistribution")
  public CompletableFuture<AnalyticsDto> calculateRatingDistribution(
      @GraphQLContext OrganisationEntity entity) {
    return entity.getRatings() != null && !entity.getRatings().isEmpty()
        ? ratingService.calculateRatingDistribution(entity.getRatings())
        : CompletableFuture.completedFuture(new AnalyticsDto());
  }
  
  @GraphQLQuery(name = "searchStatistics")
  public CompletableFuture<Set<AnalyticsDto>> searchStatistics(
      @GraphQLContext OrganisationEntity entity,
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval) throws IOException {
    return searchConsoleService.getEntitySearchStatistics(
        startDate, endDate, interval, entity);
  }
  
  @GraphQLQuery(name = "visitorStatistics")
  public CompletableFuture<Set<AnalyticsDto>> visitorStatistics(
      @GraphQLContext OrganisationEntity entity,
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval) throws Throwable {
    return visitableAnalyticsService.getEntityVisitableStatistics(
        startDate, endDate, interval, entity);
  }
}
