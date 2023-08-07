
package app.wooportal.server.core.location.model.route;

import java.util.List;
import lombok.Data;

@Data
public class ActualEnd {

  private String type;
  private List<Double> coordinates;

}
