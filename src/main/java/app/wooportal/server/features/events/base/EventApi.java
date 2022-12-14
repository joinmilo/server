package app.wooportal.server.features.events.base;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class EventApi extends CrudApi<EventEntity, EventService> {


  public EventApi(EventService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getEvents")
  @ApprovedAndVerifiedPermission
  public PageableList<EventEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getEvent")
  @ApprovedAndVerifiedPermission
  public Optional<EventEntity> readOne(@GraphQLArgument(name = CrudApi.entity) EventEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveEvents")
  @AdminPermission
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
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteEvent")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
