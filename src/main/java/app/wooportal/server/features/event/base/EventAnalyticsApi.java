package app.wooportal.server.features.event.base;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import app.wooportal.server.base.analytics.googleSearch.SearchConsoleService;
import app.wooportal.server.base.rating.RatingDto;
import app.wooportal.server.base.rating.RatingService;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class EventAnalyticsApi {
  
  private final RatingService ratingService;
  private final SearchConsoleService searchConsoleService;

  public EventAnalyticsApi(
      RatingService ratingService,
      SearchConsoleService searchConsoleService) {
    this.ratingService = ratingService;
    this.searchConsoleService = searchConsoleService;
  }
  
  @GraphQLQuery(name = "calculatedRatings")
  public CompletableFuture<RatingDto> calculateAverageRating(
      @GraphQLContext EventEntity event) {
    return ratingService.calculateRating(
        event.getRatings().stream().map(rating -> rating.getScore()).collect(Collectors.toList()));
  }
  
  @GraphQLQuery(name = "searchStatistics")
  public CompletableFuture<Set<AnalyticsDto>> searchConsoleEventDetails(
      @GraphQLContext EventEntity event,
      OffsetDateTime startDate,
      OffsetDateTime endDate) throws IOException {
    return searchConsoleService.getEntitySearchStatistics(startDate, endDate, event);
  }
}
