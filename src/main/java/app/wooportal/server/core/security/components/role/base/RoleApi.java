package app.wooportal.server.core.security.components.role.base;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.components.user.authorization.permissions.UserAdminPermission;
import app.wooportal.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class RoleApi extends CrudApi<RoleEntity, RoleService> {

  public RoleApi(
      RoleService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getRoles")
  @AdminPermission
  public PageableList<RoleEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getRole")
  @AdminPermission
  public Optional<RoleEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) RoleEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveRoles")
  @AdminPermission
  public List<RoleEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<RoleEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveRole")
  @AdminPermission
  public RoleEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) RoleEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteRoles")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteRole")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLMutation(name = "addUser")
  @UserAdminPermission
  public Boolean addUser(
      String userId,
      String roleId) {
    return service.addUser(userId, roleId);
  }
}
