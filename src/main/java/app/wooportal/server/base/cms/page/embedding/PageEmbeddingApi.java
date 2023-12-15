package app.wooportal.server.base.cms.page.embedding;

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
public class PageEmbeddingApi extends CrudApi<PageEmbeddingEntity, PageEmbeddingService> {

  public PageEmbeddingApi(PageEmbeddingService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getPageEmbeddings")
  public PageableList<PageEmbeddingEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getPageEmbedding")
  public Optional<PageEmbeddingEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) PageEmbeddingEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "savePageEmbeddings")
  @CmsAdminPermission
  public List<PageEmbeddingEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<PageEmbeddingEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "savePageEmbedding")
  @CmsAdminPermission
  public PageEmbeddingEntity saveOne(@GraphQLArgument(name = CrudApi.entity) PageEmbeddingEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deletePageEmbeddings")
  @CmsAdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deletePageEmbedding")
  @CmsAdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
