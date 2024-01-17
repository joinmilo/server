package app.wooportal.server.base.cms.components.page.embeddingType;

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
public class PageEmbeddingTypeApi extends CrudApi<PageEmbeddingTypeEntity, PageEmbeddingTypeService> {

  public PageEmbeddingTypeApi(PageEmbeddingTypeService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getPageEmbeddingTypes")
  public PageableList<PageEmbeddingTypeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getPageEmbeddingType")
  public Optional<PageEmbeddingTypeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) PageEmbeddingTypeEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "savePageEmbeddingTypes")
  @CmsAdminPermission
  public List<PageEmbeddingTypeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<PageEmbeddingTypeEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "savePageEmbeddingType")
  @CmsAdminPermission
  public PageEmbeddingTypeEntity saveOne(@GraphQLArgument(name = CrudApi.entity) PageEmbeddingTypeEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deletePageEmbeddingTypes")
  @CmsAdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deletePageEmbeddingType")
  @CmsAdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
