package app.wooportal.server.features.organisation.comment;

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
public class OrganisationCommentApi
    extends CrudApi<OrganisationCommentEntity, OrganisationCommentService> {

  public OrganisationCommentApi(OrganisationCommentService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getOrganisationComments")
  public PageableList<OrganisationCommentEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getOrganisationComment")
  public Optional<OrganisationCommentEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) OrganisationCommentEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveOrganisationComments")
  @AdminPermission
  public List<OrganisationCommentEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<OrganisationCommentEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveOrganisationComment")
  public OrganisationCommentEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) OrganisationCommentEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganisationComments")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganisationComment")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
