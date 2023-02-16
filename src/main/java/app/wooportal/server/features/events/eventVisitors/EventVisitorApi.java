package app.wooportal.server.features.events.eventVisitors;

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
public class EventVisitorApi extends CrudApi<EventVisitorEntity, EventVisitorService> {


  public EventVisitorApi(EventVisitorService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getEventVisitors")
  public PageableList<EventVisitorEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getEventVisitor")
  public Optional<EventVisitorEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) EventVisitorEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveEventVisitors")
  public List<EventVisitorEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<EventVisitorEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveEventVisitor")
  public EventVisitorEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) EventVisitorEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteEventVisitors")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteEventVisitor")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
