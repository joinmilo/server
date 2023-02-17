package app.wooportal.server.features.events.categories;

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
public class EventCategoryApi extends CrudApi<EventCategoryEntity, EventCategoryService> {


  public EventCategoryApi(EventCategoryService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getEventCategories")
  @ApprovedAndVerifiedPermission
  public PageableList<EventCategoryEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getEventCategory")
  @ApprovedAndVerifiedPermission
  public Optional<EventCategoryEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) EventCategoryEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveEventCategories")
  @AdminPermission
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
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteEventCategory")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
