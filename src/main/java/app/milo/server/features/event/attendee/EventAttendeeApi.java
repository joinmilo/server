package app.milo.server.features.event.attendee;

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
public class EventAttendeeApi extends CrudApi<EventAttendeeEntity, EventAttendeeService> {

  public EventAttendeeApi(EventAttendeeService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getEventAttendees")
  public PageableList<EventAttendeeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getEventAttendee")
  public Optional<EventAttendeeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) EventAttendeeEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveEventAttendees")
  public List<EventAttendeeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<EventAttendeeEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveEventAttendee")
  public EventAttendeeEntity saveOne(@GraphQLArgument(name = CrudApi.entity) EventAttendeeEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteEventAttendees")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteEventAttendee")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
