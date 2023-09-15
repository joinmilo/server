package app.wooportal.server.base.analytics.rating;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;
import app.wooportal.server.base.configuration.ConfigurationService;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;

@Service
public class RatingService {

  private final Integer maxRating;

  public RatingService(ConfigurationService configurationService) {
    maxRating = configurationService
        .readOne(configurationService
            .singleQuery(configurationService.getPredicate().withCode("maxRating")))
        .map(config -> Integer.valueOf(config.getValue()))
        .orElse(5);
  }

  public <E extends BaseEntity, R extends RatableEntity<E>> CompletableFuture<AnalyticsDto>
      calculateRatingDistribution(Collection<R> ratings) {
    var result = new AnalyticsDto().setName("scoreDistribution");

    IntStream.rangeClosed(1, maxRating)
      .forEach(i -> result.add(Integer.toString(i), 0.0));

    for (var rating : ratings) {
      if (rating.getScore() != null) {
        result.add(rating.getScore().toString(), 1);
      }
    }

    return CompletableFuture.completedFuture(result);
  }
}
