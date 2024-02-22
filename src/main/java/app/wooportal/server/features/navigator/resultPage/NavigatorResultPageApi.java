package app.wooportal.server.features.navigator.resultPage;

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
public class NavigatorResultPageApi extends CrudApi<NavigatorResultPageEntity, NavigatorResultPageService> {

  public NavigatorResultPageApi(NavigatorResultPageService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getNavigatorResultPages")
  public PageableList<NavigatorResultPageEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getNavigatorResultPage")
  public Optional<NavigatorResultPageEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) NavigatorResultPageEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveNavigatorResultPages")
  @AdminPermission
  public List<NavigatorResultPageEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<NavigatorResultPageEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveNavigatorResultPage")
  @AdminPermission
  public NavigatorResultPageEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) NavigatorResultPageEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteNavigatorResultPages")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteNavigatorResultPage")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
