package app.milo.server.core.location;

import javax.naming.ServiceUnavailableException;
import app.milo.server.base.address.base.AddressEntity;
import app.milo.server.core.error.exception.NotFoundException;
import app.milo.server.core.location.dto.LocationParam;
import app.milo.server.core.location.model.route.RouteResource;

public interface MapService {
  
  public AddressEntity retrieveExternalAddress(AddressEntity address)
      throws ServiceUnavailableException, NotFoundException;
  
  public RouteResource calculateRoute(LocationParam params) 
      throws ServiceUnavailableException, NotFoundException;
}
