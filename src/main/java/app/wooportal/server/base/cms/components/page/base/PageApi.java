package app.wooportal.server.base.cms.components.page.base;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.base.cms.authorization.permissions.CmsAdminPermission;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.features.event.authorization.permissions.EventAdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class PageApi extends CrudApi<PageEntity, PageService> {

  public PageApi(PageService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getPages")
  public PageableList<PageEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getPage")
  public Optional<PageEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) PageEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "savePages")
  @CmsAdminPermission
  public List<PageEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<PageEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "savePage")
  @CmsAdminPermission
  public PageEntity saveOne(@GraphQLArgument(name = CrudApi.entity) PageEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deletePages")
  @CmsAdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deletePage")
  @CmsAdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLMutation(name = "assignLandingPage")
  @EventAdminPermission
  public Boolean assignLanding(String pageId) {
    return service.assignLanding(pageId);
  }
}
