package app.milo.server.base.milestone.media;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.milo.server.core.base.CrudApi;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class MilestoneMediaApi extends CrudApi<MilestoneMediaEntity, MilestoneMediaService> {

  public MilestoneMediaApi(MilestoneMediaService service) {
    super(service);
  }
  
  @Override
  @GraphQLQuery(name = "getMilestoneMedia")
  public PageableList<MilestoneMediaEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getMilestoneMedium")
  public Optional<MilestoneMediaEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) MilestoneMediaEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveMilestoneMedia")
  public List<MilestoneMediaEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<MilestoneMediaEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveMilestoneMedium")
  public MilestoneMediaEntity saveOne(@GraphQLArgument(name = CrudApi.entity) MilestoneMediaEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteMilestoneMedia")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteMilestoneMedium")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
