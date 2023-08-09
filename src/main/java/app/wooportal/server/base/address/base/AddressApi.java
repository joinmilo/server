package app.wooportal.server.base.address.base;

import java.util.List;
import java.util.Optional;
import javax.naming.ServiceUnavailableException;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.location.MapService;
import app.wooportal.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class AddressApi extends CrudApi<AddressEntity, AddressService> {

  private final MapService mapService; 
  
  public AddressApi(AddressService service, MapService mapService) {
    super(service);
    
    this.mapService = mapService;
  }

  @Override
  @GraphQLQuery(name = "getAddresses")
  public PageableList<AddressEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getAddress")
  public Optional<AddressEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AddressEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveAddresses")
  @AdminPermission
  public List<AddressEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AddressEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveAddress")
  public AddressEntity saveOne(@GraphQLArgument(name = CrudApi.entity) AddressEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteAddresses")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteAddress")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLMutation(name = "verifyAddress")
  public AddressEntity verifyAddress(
      @GraphQLArgument(name = CrudApi.entity) AddressEntity entity) throws NotFoundException, ServiceUnavailableException {
    return mapService.retrieveExternalAddress(entity);
  }
}
