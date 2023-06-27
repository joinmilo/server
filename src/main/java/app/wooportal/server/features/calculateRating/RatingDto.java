package app.wooportal.server.features.calculateRating;

import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class RatingDto {

  private Double average;

  private Map<String, Double> distribution;
  
  private Integer totalReviews;
}
