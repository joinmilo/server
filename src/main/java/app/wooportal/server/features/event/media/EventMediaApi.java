package app.wooportal.server.features.event.media;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.features.event.authorization.permissions.EventMediaManagePermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class EventMediaApi extends CrudApi<EventMediaEntity, EventMediaService> {

  public EventMediaApi(EventMediaService service) {
    super(service);
  }
  
  @Override
  @GraphQLQuery(name = "getEventMedia")
  public PageableList<EventMediaEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getEventMedium")
  public Optional<EventMediaEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) EventMediaEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveEventMedia")
  @EventMediaManagePermission
  public List<EventMediaEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<EventMediaEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveEventMedium")
  @EventMediaManagePermission
  public EventMediaEntity saveOne(@GraphQLArgument(name = CrudApi.entity) EventMediaEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteEventMedia")
  @EventMediaManagePermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteEventMedium")
  @EventMediaManagePermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
