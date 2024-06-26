package app.milo.server.base.cms.components.menuItem;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.milo.server.base.cms.authorization.permissions.CmsAdminPermission;
import app.milo.server.core.base.CrudApi;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.listing.PageableList;
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
  @CmsAdminPermission
  public List<MenuItemEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<MenuItemEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveMenuItem")
  @CmsAdminPermission
  public MenuItemEntity saveOne(@GraphQLArgument(name = CrudApi.entity) MenuItemEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteMenuItems")
  @CmsAdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteMenuItem")
  @CmsAdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
