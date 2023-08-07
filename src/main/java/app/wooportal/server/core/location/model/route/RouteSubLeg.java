
package app.wooportal.server.core.location.model.route;

import lombok.Data;

@Data
public class RouteSubLeg {

  private EndWaypoint endWaypoint;
  private StartWaypoint startWaypoint;
  private Double travelDistance;
  private Integer travelDuration;

}
