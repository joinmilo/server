package app.wooportal.server.base.userContext.base;

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
public class UserContextApi extends CrudApi<UserContextEntity, UserContextService> {

  public UserContextApi(UserContextService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getUserContexts")
  public PageableList<UserContextEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getUserContext")
  public Optional<UserContextEntity> readOne(@GraphQLArgument(name = CrudApi.entity) UserContextEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveUserContexts")
  @AdminPermission
  public List<UserContextEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<UserContextEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveUserContext")
  public UserContextEntity saveOne(@GraphQLArgument(name = CrudApi.entity) UserContextEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteUserContexts")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteUserContext")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLQuery(name = "me")
  public Optional<UserContextEntity> me() {
    return service.me();
  }
}
