package app.wooportal.server.base.cms.menuItem;

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
public class MenuItemApi extends CrudApi<MenuItemEntity, MenuItemService> {

  public MenuItemApi(MenuItemService menuItemService) {
    super(menuItemService);
  }

  @Override
  @GraphQLQuery(name = "getMenuItems")
  public PageableList<MenuItemEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getMenuItem")
  public Optional<MenuItemEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) MenuItemEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveMenuItems")
  @AdminPermission
  public List<MenuItemEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<MenuItemEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveMenuItem")
  @AdminPermission
  public MenuItemEntity saveOne(@GraphQLArgument(name = CrudApi.entity) MenuItemEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteMenuItems")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteMenuItem")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
