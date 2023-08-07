
package app.wooportal.server.core.location.model.route;

import java.util.List;
import lombok.Data;

@Data
public class RoutePath {

  public List<Object> generalizations;
  public Line line;

}
