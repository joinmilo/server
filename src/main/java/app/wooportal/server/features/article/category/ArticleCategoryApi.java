package app.wooportal.server.features.article.category;

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
public class ArticleCategoryApi extends CrudApi<ArticleCategoryEntity, ArticleCategoryService> {

  public ArticleCategoryApi(ArticleCategoryService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getArticleCategories")
  public PageableList<ArticleCategoryEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getArticleCategory")
  public Optional<ArticleCategoryEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ArticleCategoryEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveArticleCategories")
  @AdminPermission
  public List<ArticleCategoryEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ArticleCategoryEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveArticleCategory")
  public ArticleCategoryEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) ArticleCategoryEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteArticleCategories")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteArticleCategory")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
