package app.wooportal.server.base.address.base;

import java.util.Optional;
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
  public Optional<AddressEntity> getExisting(AddressEntity entity) {
    return entity != null
        && entity.getStreet() != null
        && entity.getHouseNumber() != null
        && entity.getPlace() != null
        && entity.getPostalCode() != null
          ? getByAddress(entity)
          : Optional.empty();
  }
  
  private Optional<AddressEntity> getByAddress(AddressEntity entity) {
    var result = repo.findAll(collectionQuery(predicate.withStreet(entity.getStreet()))
        .and(predicate.withHouseNumber(entity.getHouseNumber()))
        .and(predicate.withPlace(entity.getPlace()))
        .and(predicate.withPostalCode(entity.getPostalCode()))
    );
    
    return result != null && !result.isEmpty()
        ? Optional.ofNullable(result.get(0))
        : Optional.empty();
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
