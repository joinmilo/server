package app.wooportal.server.base.userDeletion.type;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class UserDeletionTypeApi extends CrudApi<UserDeletionTypeEntity, UserDeletionTypeService> {


  public UserDeletionTypeApi(UserDeletionTypeService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getUserDeletionTypes")
  public PageableList<UserDeletionTypeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getUserDeletionType")
  public Optional<UserDeletionTypeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) UserDeletionTypeEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveUserDeletionTypes")
  public List<UserDeletionTypeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<UserDeletionTypeEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveUserDeletionType")
  public UserDeletionTypeEntity saveOne(@GraphQLArgument(name = CrudApi.entity) UserDeletionTypeEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteUserDeletionTypes")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteUserDeletionType")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}
