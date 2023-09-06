package app.wooportal.server.base.address.base;

import javax.naming.ServiceUnavailableException;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.base.address.suburb.SuburbService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.location.MapService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AddressService extends DataService<AddressEntity, AddressPredicateBuilder> {
  
  private final MapService mapService;

  public AddressService(DataRepository<AddressEntity> repo, 
      AddressPredicateBuilder predicate, 
      MapService mapService,
      SuburbService suburbService) {
    super(repo, predicate);
    
    addService("suburb", suburbService);
    
    this.mapService = mapService;
  }
  
  @Override
  public void preSave(AddressEntity entity, AddressEntity newEntity, JsonNode context) {

    try {
     var address = this.mapService.retrieveExternalAddress(newEntity);
     newEntity.setLatitude(address.getLatitude());
     newEntity.setLongitude(address.getLongitude());
     addContext("longitude", context);
     addContext("latitude", context);
    } catch (NotFoundException e) {
      e.printStackTrace();
    } catch (ServiceUnavailableException e) {
      e.printStackTrace();
    }
  }
}
