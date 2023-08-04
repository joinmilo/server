package app.wooportal.server.features.article.rating;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ArticleRatingApi extends CrudApi<ArticleRatingEntity, ArticleRatingService> {

  public ArticleRatingApi(ArticleRatingService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getArticleRatings")
  public PageableList<ArticleRatingEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getArticleRating")
  public Optional<ArticleRatingEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ArticleRatingEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveArticleRatings")
  public List<ArticleRatingEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ArticleRatingEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveArticleRating")
  public ArticleRatingEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) ArticleRatingEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteArticleRatings")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteArticleRating")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
