package app.wooportal.server.features.event.attendee;

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
public class AttendeeApi extends CrudApi<AttendeeEntity, AttendeeService> {


  public AttendeeApi(AttendeeService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getAttendees")
  public PageableList<AttendeeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getAttendee")
  public Optional<AttendeeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AttendeeEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveAttendees")
  public List<AttendeeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AttendeeEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveAttendee")
  public AttendeeEntity saveOne(@GraphQLArgument(name = CrudApi.entity) AttendeeEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteAttendees")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteAttendee")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
