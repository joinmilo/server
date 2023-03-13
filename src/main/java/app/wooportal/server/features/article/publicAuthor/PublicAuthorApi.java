package app.wooportal.server.features.article.publicAuthor;

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
public class PublicAuthorApi extends CrudApi<PublicAuthorEntity, PublicAuthorService> {


  public PublicAuthorApi(PublicAuthorService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getPublicAuthors")
  public PageableList<PublicAuthorEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getPublicAuthor")
  public Optional<PublicAuthorEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) PublicAuthorEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "savePublicAuthors")
  @AdminPermission
  public List<PublicAuthorEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<PublicAuthorEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "savePublicAuthor")
  public PublicAuthorEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) PublicAuthorEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deletePublicAuthors")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deletePublicAuthor")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
