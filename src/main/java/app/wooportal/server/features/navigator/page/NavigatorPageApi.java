package app.wooportal.server.features.navigator.page;

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
public class NavigatorPageApi extends CrudApi<NavigatorPageEntity, NavigatorPageService> {

  public NavigatorPageApi(NavigatorPageService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getNavigatorPages")
  public PageableList<NavigatorPageEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getNavigatorPage")
  public Optional<NavigatorPageEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) NavigatorPageEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveNavigatorPages")
  @AdminPermission
  public List<NavigatorPageEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<NavigatorPageEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveNavigatorPage")
  @AdminPermission
  public NavigatorPageEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) NavigatorPageEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteNavigatorPages")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteNavigatorPage")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

//  @GraphQLQuery(name = "possibleChildPages")
//  public List<NavigatorPageEntity> possibleChildPages(
//      @GraphQLContext NavigatorPageEntity page) {
//    return service.getPossiblePages(page);
//  }
  
  @GraphQLQuery(name = "getNavigatorStartPage")
  public NavigatorPageEntity getNavigatorStartPage() {
    return service.getStartPage();
  }
}
