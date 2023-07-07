package app.wooportal.server.base.rating;

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

  private Map<Integer, Double> distribution;

  private Integer total;
}
