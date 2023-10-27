package app.wooportal.server.features.article.components.base;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.features.article.authorization.permissions.ArticleAdminPermission;
import app.wooportal.server.features.article.authorization.permissions.ArticleManagePermission;
import app.wooportal.server.features.article.components.comment.ArticleCommentEntity;
import app.wooportal.server.features.article.components.comment.ArticleCommentService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ArticleApi extends CrudApi<ArticleEntity, ArticleService> {

  private final ArticleCommentService commentService;

  public ArticleApi(
      ArticleService service,
      ArticleCommentService commentService) {
    super(service);
    
    this.commentService = commentService;
  }
  
  @Override
  @GraphQLQuery(name = "getArticles")
  public PageableList<ArticleEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getArticle")
  public Optional<ArticleEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ArticleEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveArticles")
  @ArticleManagePermission
  public List<ArticleEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ArticleEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveArticle")
  @ArticleManagePermission
  public ArticleEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ArticleEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteArticles")
  @ArticleManagePermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteArticle")
  @ArticleManagePermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLMutation(name = "saveGuestArticle")
  public ArticleEntity saveGuestArticle(@GraphQLArgument(name = CrudApi.entity) ArticleEntity entity) {
    return service.saveGuestArticle(entity);
  }
  
  @GraphQLQuery(name = "lastArticleComment")
  public Optional<ArticleCommentEntity> getLastComment(
      @GraphQLContext ArticleEntity article) {
    return commentService.getMostRecentByArticle(article.getId());
  }
  
  @GraphQLMutation(name = "changeArticleApproval")
  @ArticleAdminPermission
  public Boolean changeArticleApproval(String articleId) {
    return service.changeApproval(articleId);
  }
  
  @GraphQLMutation(name = "sponsorArticle")
  @ArticleAdminPermission
  public Boolean sponsorArticle(String articleId) {
    return service.sponsor(articleId);
  }

}
