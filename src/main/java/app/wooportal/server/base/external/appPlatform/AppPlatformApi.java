package app.wooportal.server.base.external.appPlatform;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class AppPlatformApi extends CrudApi<AppPlatformEntity, AppPlatformService> {

  public AppPlatformApi(AppPlatformService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getAppPlatforms")
  public PageableList<AppPlatformEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getAppPlatform")
  public Optional<AppPlatformEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AppPlatformEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveAppPlatforms")
  @AdminPermission
  public List<AppPlatformEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AppPlatformEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveAppPlatform")
  public AppPlatformEntity saveOne(@GraphQLArgument(name = CrudApi.entity) AppPlatformEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteAppPlatforms")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteAppPlatform")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
