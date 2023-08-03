package app.wooportal.server.features.article.base.media;

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
public class ArticleMediaApi extends CrudApi<ArticleMediaEntity, ArticleMediaService> {

  public ArticleMediaApi(ArticleMediaService service) {
    super(service);
  }
  
  @Override
  @GraphQLQuery(name = "getArticleMedia")
  public PageableList<ArticleMediaEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getArticleMedium")
  public Optional<ArticleMediaEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ArticleMediaEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveArticleMedia")
  @AdminPermission
  public List<ArticleMediaEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ArticleMediaEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveArticleMedium")
  public ArticleMediaEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ArticleMediaEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteArticleMedia")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteArticleMedium")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
