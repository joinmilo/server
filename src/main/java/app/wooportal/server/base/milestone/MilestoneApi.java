package app.wooportal.server.base.milestone;

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
public class MilestoneApi extends CrudApi<MilestoneEntity, MilestoneService> {


  public MilestoneApi(MilestoneService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getMilestones")
  public PageableList<MilestoneEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getMilestone")
  public Optional<MilestoneEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) MilestoneEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveMilestones")
  @AdminPermission
  public List<MilestoneEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<MilestoneEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveMilestone")
  public MilestoneEntity saveOne(@GraphQLArgument(name = CrudApi.entity) MilestoneEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteMilestones")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteMilestone")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
