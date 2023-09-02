package app.wooportal.server.base.userDeletion.base;

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
public class UserDeletionApi extends CrudApi<UserDeletionEntity, UserDeletionService> {


  public UserDeletionApi(UserDeletionService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getUserDeletions")
  public PageableList<UserDeletionEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getUserDeletion")
  public Optional<UserDeletionEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) UserDeletionEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveUserDeletions")
  @AdminPermission
  public List<UserDeletionEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<UserDeletionEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveUserDeletion")
  public UserDeletionEntity saveOne(@GraphQLArgument(name = CrudApi.entity) UserDeletionEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteUserDeletions")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteUserDeletion")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
