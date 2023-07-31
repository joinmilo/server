package app.wooportal.server.core.security.components.roleApplication;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class RoleApplicationApi extends CrudApi<RoleApplicationEntity, RoleApplicationService> {

  public RoleApplicationApi(
      RoleApplicationService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getRoleApplications")
  @AdminPermission
  public PageableList<RoleApplicationEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getRoleApplication")
  @AdminPermission
  public Optional<RoleApplicationEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) RoleApplicationEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveRoleApplications")
  @AdminPermission
  public List<RoleApplicationEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<RoleApplicationEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveRoleApplication")
  @AdminPermission
  public RoleApplicationEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) RoleApplicationEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteRoleApplications")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteRoleApplication")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}