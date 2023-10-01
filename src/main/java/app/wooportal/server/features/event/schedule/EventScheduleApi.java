package app.wooportal.server.features.event.schedule;

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
public class EventScheduleApi extends CrudApi<EventScheduleEntity, EventScheduleService> {


  public EventScheduleApi(EventScheduleService scheduleService) {
    super(scheduleService);
  }

  @Override
  @GraphQLQuery(name = "getEventSchedules")
  public PageableList<EventScheduleEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getEventSchedule")
  public Optional<EventScheduleEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) EventScheduleEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveEventSchedules")
  public List<EventScheduleEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<EventScheduleEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveEventSchedule")
  public EventScheduleEntity saveOne(@GraphQLArgument(name = CrudApi.entity) EventScheduleEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteEventSchedules")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteEventSchedule")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
