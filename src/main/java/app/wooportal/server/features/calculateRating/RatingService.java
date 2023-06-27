package app.wooportal.server.features.calculateRating;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

  public CompletableFuture<RatingDto> calculateRating(int[] scores) {
    if (scores != null && scores.length > 0) {

      RatingDto ratingDto = new RatingDto();

      ratingDto.setDistribution(calculateClusteredAverages(scores));
      ratingDto.setAverage(calculateTotalAverage(scores));
      ratingDto.setTotalReviews(scores.length);

      return CompletableFuture.completedFuture(ratingDto);
    }
    return null;
  }

  private Map<String, Double> calculateClusteredAverages(int[] scores) {

    Map<String, Double> groupDistribution = new HashMap<>();

    for (int i = 0; i < 5; i++) {
      Integer group = i + 1;
      String groupName = group.toString();

      double distribution = (double) Arrays.stream(scores)
          .filter(score -> score == group)
          .count() / scores.length * 100;

      groupDistribution.put(groupName, distribution);
    }
    return groupDistribution;
  }

  private double calculateTotalAverage(int[] scores) {
    
    return Arrays.stream(scores).average().orElse(0.0);

  }
}
