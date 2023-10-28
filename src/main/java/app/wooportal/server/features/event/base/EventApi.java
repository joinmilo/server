package app.wooportal.server.features.event.base;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.features.event.authorization.permissions.EventAdminPermission;
import app.wooportal.server.features.event.authorization.permissions.EventManagePermission;
import app.wooportal.server.features.event.comment.EventCommentEntity;
import app.wooportal.server.features.event.comment.EventCommentService;
import app.wooportal.server.features.event.schedule.EventScheduleEntity;
import app.wooportal.server.features.event.schedule.EventScheduleService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class EventApi extends CrudApi<EventEntity, EventService> {
  
  private final EventCommentService commentService;
  
  private final EventScheduleService scheduleService;

  public EventApi(
      EventService service,
      EventCommentService commentService,
      EventScheduleService scheduleService) {
    super(service);
    
    this.commentService = commentService;
    this.scheduleService = scheduleService;
  }

  @Override
  @GraphQLQuery(name = "getEvents")
  public PageableList<EventEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getEvent")
  public Optional<EventEntity> readOne(@GraphQLArgument(name = CrudApi.entity) EventEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveEvents")
  @EventManagePermission  
  public List<EventEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<EventEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveEvent")
  @EventManagePermission
  public EventEntity saveOne(@GraphQLArgument(name = CrudApi.entity) EventEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteEvents")
  @EventManagePermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteEvent")
  @EventManagePermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

  @GraphQLQuery(name = "lastEventComment")
  public Optional<EventCommentEntity> getLastComment(
      @GraphQLContext EventEntity event) {
    return commentService.getMostRecentByEvent(event.getId());
  }
  
  @GraphQLQuery(name = "schedule")
  public Optional<EventScheduleEntity> getSchedule(
      @GraphQLContext EventEntity event,
      OffsetDateTime begin,
      OffsetDateTime end) {
    
    return begin != null && end != null
        ? scheduleService.getByEventAndBetween(event.getId(), begin, end)
        : scheduleService.getMostRecentByEvent(event.getId());
  }
  
  @GraphQLQuery(name = "hasSchedules")
  public Boolean hasSchedules(
      @GraphQLContext EventEntity event) {
    return scheduleService.hasSchedules(event.getId());
  }
  
  @GraphQLMutation(name = "sponsorEvent")
  @EventAdminPermission
  public Boolean sponsorEvent(String eventId) {
    return service.sponsor(eventId);
  }

}
