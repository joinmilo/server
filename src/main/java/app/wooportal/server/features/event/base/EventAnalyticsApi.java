package app.wooportal.server.features.event.base;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import app.wooportal.server.base.analytics.googleSearch.GoogleSearchDto;
import app.wooportal.server.base.analytics.googleSearch.SearchAnalyticsDto;
import app.wooportal.server.base.analytics.googleSearch.SearchConsoleService;
import app.wooportal.server.base.analytics.googleSearch.SearchDimension;
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
  
  @GraphQLQuery(name = "googleSearchEventDetails")
  public List<GoogleSearchDto> searchConsoleEventDetails(@GraphQLContext EventEntity event,
      LocalDate startDate, LocalDate endDate) throws IOException {

    if (startDate == null || endDate == null) {
      endDate = LocalDate.now();
      startDate = LocalDate.of(endDate.getYear(), 1, 1);
      
    } 
    OffsetDateTime targetDateTime = OffsetDateTime.of(2023, 10, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    var keyword = event.getCreated().isBefore(targetDateTime) ? event.getId() : "events" + event.getSlug();
    return searchConsoleService.calculateForFeature(startDate, endDate, keyword);
  }
}
