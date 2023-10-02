package app.wooportal.server.features.article.components.comment;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.permissions.Authenticated;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ArticleCommentApi extends CrudApi<ArticleCommentEntity, ArticleCommentService> {


  public ArticleCommentApi(ArticleCommentService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getArticleComments")
  public PageableList<ArticleCommentEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getArticleComment")
  public Optional<ArticleCommentEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ArticleCommentEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveArticleComments")
  @Authenticated
  public List<ArticleCommentEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ArticleCommentEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveArticleComment")
  @Authenticated
  public ArticleCommentEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ArticleCommentEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteArticleComments")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteArticleComment")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
