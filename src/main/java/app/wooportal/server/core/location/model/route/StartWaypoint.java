
package app.wooportal.server.core.location.model.route;

import java.util.List;

import lombok.Data;

@Data
public class StartWaypoint {

  private String type;
  private List<Double> coordinates;
  private String description;
  private Boolean isVia;
  private String locationIdentifier;
  private Integer routePathIndex;

}
