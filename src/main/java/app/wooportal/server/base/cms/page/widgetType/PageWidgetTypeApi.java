package app.wooportal.server.base.cms.page.widgetType;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.base.cms.authorization.permissions.CmsAdminPermission;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class PageWidgetTypeApi extends CrudApi<PageWidgetTypeEntity, PageWidgetTypeService> {

  public PageWidgetTypeApi(PageWidgetTypeService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getPageWidgetTypes")
  public PageableList<PageWidgetTypeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getPageWidgetType")
  public Optional<PageWidgetTypeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) PageWidgetTypeEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "savePageWidgetTypes")
  @CmsAdminPermission
  public List<PageWidgetTypeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<PageWidgetTypeEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "savePageWidgetType")
  @CmsAdminPermission
  public PageWidgetTypeEntity saveOne(@GraphQLArgument(name = CrudApi.entity) PageWidgetTypeEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deletePageWidgetTypes")
  @CmsAdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deletePageWidgetType")
  @CmsAdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
