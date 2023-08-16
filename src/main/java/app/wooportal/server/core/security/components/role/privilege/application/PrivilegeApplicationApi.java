package app.wooportal.server.core.security.components.role.privilege.application;

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
public class PrivilegeApplicationApi extends CrudApi<PrivilegeApplicationEntity, PrivilegeApplicationService> {

  public PrivilegeApplicationApi(
      PrivilegeApplicationService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getPrivilegeApplications")
  @AdminPermission
  public PageableList<PrivilegeApplicationEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getPrivilegeApplication")
  @AdminPermission
  public Optional<PrivilegeApplicationEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) PrivilegeApplicationEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "savePrivilegeApplications")
  @AdminPermission
  public List<PrivilegeApplicationEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<PrivilegeApplicationEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "savePrivilegeApplication")
  @AdminPermission
  public PrivilegeApplicationEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) PrivilegeApplicationEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deletePrivilegeApplications")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deletePrivilegeApplication")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}
