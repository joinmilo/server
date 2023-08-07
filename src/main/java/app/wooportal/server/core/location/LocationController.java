package app.wooportal.server.core.location;

import static org.springframework.http.ResponseEntity.ok;
import javax.naming.ServiceUnavailableException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.location.dto.LocationParam;

@RestController
public class LocationController {
  
  private final BingMapService mapService;
  
  public LocationController(
      BingMapService mapService) {
    this.mapService = mapService;
  }
  
  /**
   * TODO: create graphql for that
   */
  @GetMapping("/locations")
  public ResponseEntity<?> calculateRoute(
      LocationParam params) throws ServiceUnavailableException {
    if (isValid(params)) {
      return ok(mapService.calculateRoute(params)); 
    }
    throw new BadParamsException("Start or target is empty");
  }

  private boolean isValid(LocationParam params) {
    return params.getStartPoint() != null
        && (params.getStartPoint().getLatitude() != 0 
          || params.getStartPoint().getLongitude() != 0)
      && params.getTargetPoint() != null
        && (params.getTargetPoint().getLatitude() != 0
          || params.getTargetPoint().getLongitude() != 0);
  }

}
