package app.wooportal.server.features.event.base;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import app.wooportal.server.base.rating.RatingDto;
import app.wooportal.server.base.rating.RatingService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class EventAnalyticsApi {
  
  private final RatingService ratingService;

  public EventAnalyticsApi(
      RatingService ratingService) {
    this.ratingService = ratingService;
  }
  
  @GraphQLQuery(name = "calculatedRatings")
  public CompletableFuture<RatingDto> calculateAverageRating(
      @GraphQLContext EventEntity event) {
    return ratingService.calculateRating(
        event.getRatings().stream().map(rating -> rating.getScore()).collect(Collectors.toList()));
  }
  
//  @GraphQLQuery(name = "processRejectDistribution")
//  public CompletableFuture<AnalyticsDto> getOngoingRejectDistribution(
//      @GraphQLContext AssemblyGroupEntity assemblyGroup,
//      @GraphQLArgument(name = "fromDate") OffsetDateTime fromDate,
//      @GraphQLArgument(name = "toDate") OffsetDateTime toDate,
//      @GraphQLArgument(name = "interval") IntervalFilter interval) {
//    if (assemblyGroup == null) {
//      throw new BadParamsException("AssemblyGroup or dates are missing", assemblyGroup, fromDate,
//          toDate);
//    }
//    return CompletableFuture.supplyAsync(() -> analyticsService
//        .getProcessRejectDistribution(assemblyGroup, fromDate, toDate, interval));
//  }


}
