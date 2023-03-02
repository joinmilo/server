package app.wooportal.server.base.external.appStore;

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
public class AppStoreApi extends CrudApi<AppStoreEntity, AppStoreService> {

  public AppStoreApi(AppStoreService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getAppStores")
  public PageableList<AppStoreEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getAppStore")
  public Optional<AppStoreEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AppStoreEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveAppStores")
  @AdminPermission
  public List<AppStoreEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AppStoreEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveAppStore")
  public AppStoreEntity saveOne(@GraphQLArgument(name = CrudApi.entity) AppStoreEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteAppStores")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteAppStore")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
