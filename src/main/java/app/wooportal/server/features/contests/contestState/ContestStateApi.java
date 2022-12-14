package app.wooportal.server.features.contests.contestState;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ContestStateApi extends CrudApi<ContestStateEntity, ContestStateService> {


  public ContestStateApi(ContestStateService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getContestStates")
  @ApprovedAndVerifiedPermission
  public PageableList<ContestStateEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getContestState")
  @ApprovedAndVerifiedPermission
  public Optional<ContestStateEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ContestStateEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveContestStates")
  @AdminPermission
  public List<ContestStateEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ContestStateEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveContestState")
  public ContestStateEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) ContestStateEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteContestStates")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteContestState")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
