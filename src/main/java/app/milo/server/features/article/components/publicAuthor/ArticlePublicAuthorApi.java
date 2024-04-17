package app.milo.server.features.article.components.publicAuthor;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.milo.server.core.base.CrudApi;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ArticlePublicAuthorApi extends CrudApi<ArticlePublicAuthorEntity, ArticlePublicAuthorService> {

  public ArticlePublicAuthorApi(ArticlePublicAuthorService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getArticlePublicAuthors")
  public PageableList<ArticlePublicAuthorEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getArticlePublicAuthor")
  public Optional<ArticlePublicAuthorEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ArticlePublicAuthorEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveArticlePublicAuthors")
  public List<ArticlePublicAuthorEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ArticlePublicAuthorEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveArticlePublicAuthor")
  public ArticlePublicAuthorEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) ArticlePublicAuthorEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteArticlePublicAuthors")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteArticlePublicAuthor")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
