package app.milo.server.core.security.components.role.privilege;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.milo.server.core.base.CrudApi;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.listing.PageableList;
import app.milo.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class RolePrivilegeApi extends CrudApi<RolePrivilegeEntity, RolePrivilegeService> {

  public RolePrivilegeApi(
      RolePrivilegeService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getRolePrivileges")
  @AdminPermission
  public PageableList<RolePrivilegeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getRolePrivilege")
  @AdminPermission
  public Optional<RolePrivilegeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) RolePrivilegeEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveRolePrivileges")
  @AdminPermission
  public List<RolePrivilegeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<RolePrivilegeEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveRolePrivilege")
  @AdminPermission
  public RolePrivilegeEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) RolePrivilegeEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteRolePrivileges")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteRolePrivilege")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
