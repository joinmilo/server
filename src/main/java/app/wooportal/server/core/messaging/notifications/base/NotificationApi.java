package app.wooportal.server.core.messaging.notifications.base;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.Authenticated;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class NotificationApi extends CrudApi<NotificationEntity, NotificationService> {

  public NotificationApi(NotificationService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getNotifications")
  public PageableList<NotificationEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getNotification")
  public Optional<NotificationEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) NotificationEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveNotifications")
  @Authenticated
  public List<NotificationEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<NotificationEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveNotification")
  @Authenticated
  public NotificationEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) NotificationEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteNotifications")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteNotification")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}
