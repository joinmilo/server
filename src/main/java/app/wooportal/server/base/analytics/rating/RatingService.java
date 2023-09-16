package app.wooportal.server.base.analytics.rating;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import app.wooportal.server.base.configuration.ConfigurationService;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;
import app.wooportal.server.core.base.dto.analytics.AnalyticsOperation;
import app.wooportal.server.core.base.dto.analytics.IntervalFilter;
import app.wooportal.server.core.utils.DateUtils;

@Service
public class RatingService<E extends BaseEntity, R extends RatableEntity<E>> {

  private final Integer maxRating;
  private final String maxRatingConfig = "maxRating";
  private final String scoreDistribution = "scoreDistribution";
  private final String timeAmountDistribution = "timeAmountDistribution";
  private final String timeAverageDistribution = "timeAverageDistribution";

  public RatingService(ConfigurationService configurationService) {
    maxRating = configurationService
        .readOne(configurationService
            .singleQuery(configurationService.getPredicate().withCode(maxRatingConfig)))
        .map(config -> Integer.valueOf(config.getValue()))
        .orElse(5);
  }
  
  public CompletableFuture<Set<AnalyticsDto>> calculateRatingStatistics(
      List<R> ratings,
      IntervalFilter interval) {
    if (ratings != null
        && !ratings.isEmpty()
        && interval != null) {      
      var result = new HashSet<AnalyticsDto>();
      
      result.add(ratingDistribution(ratings));
      result.addAll(timeDistributions(ratings, interval));
      
      return CompletableFuture.completedFuture(result);
    }
    return CompletableFuture.completedFuture(Set.of());
  }

  public CompletableFuture<AnalyticsDto> calculateRatingDistribution(List<R> ratings) {
    return ratings != null && !ratings.isEmpty()
        ? CompletableFuture.completedFuture(ratingDistribution(ratings))
        : CompletableFuture.completedFuture(new AnalyticsDto());
  }

  private AnalyticsDto ratingDistribution(List<R> ratings) {
    var result = new AnalyticsDto().setName(scoreDistribution);
    
    if (ratings != null && !ratings.isEmpty()) {      
      var container = calculateTotal(ratings);
      
      var totalSum = 0.0;
      for (var element : container.entrySet()) {
        totalSum += element.getKey() * element.getValue().doubleValue();
        result.add(element.getKey().toString(),
            element.getValue().doubleValue() / (double) ratings.size()
            );
      }
      
      result.setSum((double) ratings.size());
      result.setAverage(totalSum / (double) ratings.size());
    }
    
    return result;
  }

  private Map<Integer, Integer> calculateTotal(List<R> ratings) {
    var container = new HashMap<Integer, Integer>();
    IntStream.rangeClosed(1, maxRating)
      .forEach(i -> container.put(i, 0));
    
    for (var rating : ratings) {
      if (rating.getScore() != null) {
        container.put(rating.getScore(), container.containsKey(rating.getScore())
            ? container.get(rating.getScore()) + 1
            : 1);
      }
    }
    return container;
  }
  
  private Collection<? extends AnalyticsDto> timeDistributions(
      List<R> ratings,
      IntervalFilter interval) {
    Supplier<Stream<R>> ratingsSupplier = () -> ratings.stream().sorted(Comparator.comparing(BaseEntity::getModified));
    var first = ratingsSupplier.get()
        .findFirst()
        .get().getModified();
    var last = ratingsSupplier.get()
        .skip(ratings.size() - 1)
        .findFirst()
        .get().getModified();
    
    return Set.of(
        timeAmountDistribution(
            DateUtils.generatePeriodContainer(first, last, interval, 0.0),
            ratingsSupplier.get(),
            interval),
        timeAverageDistribution(
            DateUtils.generatePeriodContainer(first, last, interval, 0.0),
            ratingsSupplier.get(),
            interval)
        );
  }
  
  private AnalyticsDto timeAmountDistribution(
      Map<String,Double> container,
      Stream<R> ratings,
      IntervalFilter interval) {
    
    var result = new AnalyticsDto()
        .setName(timeAmountDistribution);

    if (container != null
        && !container.isEmpty()
        && interval != null) {
      
      ratings.forEach(rating -> result.add(
          DateUtils.format(rating.getModified(), interval), 1
      ));

    }
    
    return result;
  }
  
  private AnalyticsDto timeAverageDistribution(
      Map<String,Double> container,
      Stream<R> ratings,
      IntervalFilter interval) {
    
    var result = new AnalyticsDto()
        .setName(timeAverageDistribution)
        .setEntryOperation(AnalyticsOperation.AVG);

    if (container != null
        && !container.isEmpty()
        && interval != null) {
      
      ratings.forEach(rating -> result.add(
          DateUtils.format(rating.getModified(), interval), rating.getScore()
      ));

    }
    
    return result;
  }
}
