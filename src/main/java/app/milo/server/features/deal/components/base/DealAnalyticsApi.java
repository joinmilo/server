package app.milo.server.features.deal.components.base;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import app.milo.server.base.analytics.search.SearchConsoleService;
import app.milo.server.core.base.dto.analytics.AnalyticsDto;
import app.milo.server.core.base.dto.analytics.IntervalFilter;
import app.milo.server.core.visit.visitable.VisitableAnalyticsService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class DealAnalyticsApi {
  
  private final SearchConsoleService searchConsoleService;
  private final VisitableAnalyticsService<DealEntity, ?> visitableAnalyticsService;

  public DealAnalyticsApi(
      SearchConsoleService searchConsoleService,
      VisitableAnalyticsService<DealEntity, ?> visitableAnalyticsService) {
    this.searchConsoleService = searchConsoleService;
    this.visitableAnalyticsService = visitableAnalyticsService;
  }
  
  
  @GraphQLQuery(name = "searchStatistics")
  public CompletableFuture<Set<AnalyticsDto>> searchStatistics(
      @GraphQLContext DealEntity deal,
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval) throws IOException {
    return searchConsoleService.getEntitySearchStatistics(
        startDate, endDate, interval, deal);
  }
  
  @GraphQLQuery(name = "visitorStatistics")
  public CompletableFuture<Set<AnalyticsDto>> visitorStatistics(
      @GraphQLContext DealEntity deal,
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval) throws Throwable {
    return visitableAnalyticsService.getEntityVisitableStatistics(
        startDate, endDate, interval, deal);
  }
}
