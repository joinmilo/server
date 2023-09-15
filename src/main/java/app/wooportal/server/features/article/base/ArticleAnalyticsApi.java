package app.wooportal.server.features.article.base;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import app.wooportal.server.base.analytics.rating.RatingService;
import app.wooportal.server.base.analytics.search.SearchConsoleService;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;
import app.wooportal.server.core.base.dto.analytics.IntervalFilter;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ArticleAnalyticsApi {

  private final RatingService ratingService;
  private final SearchConsoleService searchConsoleService;

  public ArticleAnalyticsApi(
      RatingService ratingService,
      SearchConsoleService searchConsoleService) {
    
    this.ratingService = ratingService;
    this.searchConsoleService = searchConsoleService;
  }
  
  @GraphQLQuery(name = "ratingDistribution")
  public CompletableFuture<AnalyticsDto> calculateRatingDistribution(
      @GraphQLContext ArticleEntity entity) {
    return entity.getRatings() != null && !entity.getRatings().isEmpty()
        ? ratingService.calculateRatingDistribution(entity.getRatings())
        : CompletableFuture.completedFuture(new AnalyticsDto());
  }
  
  @GraphQLQuery(name = "searchStatistics")
  public CompletableFuture<Set<AnalyticsDto>> searchConsoleEventDetails(
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

}
