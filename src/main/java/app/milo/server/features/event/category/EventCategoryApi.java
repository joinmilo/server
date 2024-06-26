package app.milo.server.features.event.category;

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
public class EventCategoryApi extends CrudApi<EventCategoryEntity, EventCategoryService> {

  public EventCategoryApi(EventCategoryService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getEventCategories")
  public PageableList<EventCategoryEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getEventCategory")
  public Optional<EventCategoryEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) EventCategoryEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveEventCategories")
  public List<EventCategoryEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<EventCategoryEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveEventCategory")
  public EventCategoryEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) EventCategoryEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteEventCategories")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteEventCategory")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
