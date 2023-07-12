package app.wooportal.server.features.event.base;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import app.wooportal.server.base.rating.RatingDto;
import app.wooportal.server.base.rating.RatingService;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.features.event.comment.EventCommentEntity;
import app.wooportal.server.features.event.comment.EventCommentService;
import app.wooportal.server.features.event.schedule.ScheduleEntity;
import app.wooportal.server.features.event.schedule.ScheduleService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class EventApi extends CrudApi<EventEntity, EventService> {
  
  private final EventCommentService commentService;
  
  private final ScheduleService scheduleService;
  
  private final RatingService ratingService;

  public EventApi(
      EventService service,
      EventCommentService commentService,
      ScheduleService scheduleService,
      RatingService ratingService) {
    super(service);
    
    this.commentService = commentService;
    this.scheduleService = scheduleService;
    this.ratingService = ratingService;
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
  public List<EventEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<EventEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveEvent")
  public EventEntity saveOne(@GraphQLArgument(name = CrudApi.entity) EventEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteEvents")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteEvent")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLQuery(name = "calculatedRatings")
  public CompletableFuture<RatingDto> calculateAverageRating(
      @GraphQLContext EventEntity event) {
    return ratingService.calculateRating(
        event.getRatings().stream().map(rating -> rating.getScore()).collect(Collectors.toList()));
  }

  @GraphQLQuery(name = "lastEventComment")
  public Optional<EventCommentEntity> getLastComment(
      @GraphQLContext EventEntity event) {
    return commentService.getMostRecentByEvent(event.getId());
  }
  
  @GraphQLQuery(name = "schedule")
  public Optional<ScheduleEntity> getSchedule(
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

}
