package app.wooportal.server.base.rating;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;
import app.wooportal.server.base.configuration.ConfigurationService;

@Service
public class RatingService {
  
  private final Integer maxRating;
  
  public RatingService(
      ConfigurationService configurationService) {
    maxRating = configurationService.readOne(
        configurationService.singleQuery(
            configurationService.getPredicate().withKey("maxRating")))
    .map(config -> Integer.valueOf(config.getValue()))
    .orElse(5);
  }

  public CompletableFuture<RatingDto> calculateRating(List<Integer> scores) {
      var ratingDto = new RatingDto();

      ratingDto.setDistribution(calculateDistribution(scores));
      ratingDto.setAverage(calculateTotalAverage(scores));
      ratingDto.setTotal(scores.size());

      return CompletableFuture.completedFuture(ratingDto);
  }

  private Map<Integer, Double> calculateDistribution(List<Integer> scores) {
    var distribution = scores.stream()
        .collect(Collectors.groupingBy(
            score -> score,
            Collectors.collectingAndThen(
                Collectors.counting(),
                count -> (double) count / scores.size() * 100
                )
            ));
    
    IntStream.rangeClosed(1, maxRating)
      .forEach(i -> distribution.putIfAbsent(i, 0.0));
    
    return distribution;
  }

  private double calculateTotalAverage(List<Integer> scores) {
    return scores.stream()
        .mapToInt(Integer::intValue)
        .average().orElse(0.0);

  }
}
