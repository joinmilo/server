package app.wooportal.server.features.events.eventRatings;

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
public class EventRatingApi extends CrudApi<EventRatingEntity, EventRatingService> {


  public EventRatingApi(EventRatingService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getEventRatings")
  public PageableList<EventRatingEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getEventRating")
  public Optional<EventRatingEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) EventRatingEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveEventRatings")
  public List<EventRatingEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<EventRatingEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveEventRating")
  public EventRatingEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) EventRatingEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteEventRatings")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteEventRating")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
