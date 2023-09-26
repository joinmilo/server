package app.wooportal.server.base.milestone.element;

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
public class MilestoneElementApi extends CrudApi<MilestoneElementEntity, MilestoneElementService> {


  public MilestoneElementApi(MilestoneElementService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getMilestoneElements")
  public PageableList<MilestoneElementEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getMilestoneElement")
  public Optional<MilestoneElementEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) MilestoneElementEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveMilestoneElements")
  @AdminPermission
  public List<MilestoneElementEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<MilestoneElementEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveMilestoneElement")
  public MilestoneElementEntity saveOne(@GraphQLArgument(name = CrudApi.entity) MilestoneElementEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteMilestoneElements")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteMilestoneElement")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
