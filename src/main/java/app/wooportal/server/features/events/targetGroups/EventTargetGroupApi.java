package app.wooportal.server.features.events.targetGroups;

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
public class EventTargetGroupApi extends CrudApi<EventTargetGroupEntity, EventTargetGroupService> {


  public EventTargetGroupApi(EventTargetGroupService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getEventTargetGroups")
  public PageableList<EventTargetGroupEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getEventTargetGroup")
  public Optional<EventTargetGroupEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) EventTargetGroupEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveEventTargetGroups")
  public List<EventTargetGroupEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<EventTargetGroupEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveEventTargetGroup")
  public EventTargetGroupEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) EventTargetGroupEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteEventTargetGroups")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteEventTargetGroup")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
