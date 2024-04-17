package app.milo.server.base.address.suburb;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import app.milo.server.base.address.authorization.permissions.AddressAdminPermission;
import app.milo.server.core.base.CrudApi;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class SuburbApi extends CrudApi<SuburbEntity, SuburbService> {

  public SuburbApi(SuburbService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getSuburbs")
  public PageableList<SuburbEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getSuburb")
  public Optional<SuburbEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) SuburbEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveSuburbs")
  @AddressAdminPermission
  public List<SuburbEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<SuburbEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveSuburb")
  @AddressAdminPermission
  public SuburbEntity saveOne(@GraphQLArgument(name = CrudApi.entity) SuburbEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteSuburbs")
  @AddressAdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteSuburb")
  @AddressAdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
