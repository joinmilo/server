package app.wooportal.server.core.location;

import javax.naming.ServiceUnavailableException;
import app.wooportal.server.base.address.base.AddressEntity;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.location.dto.LocationParam;
import app.wooportal.server.core.location.model.route.RouteResource;

public interface MapService {
  
  public AddressEntity retrieveExternalAddress(AddressEntity address)
      throws ServiceUnavailableException, NotFoundException;
  
  public RouteResource calculateRoute(LocationParam params) 
      throws ServiceUnavailableException, NotFoundException;

}
