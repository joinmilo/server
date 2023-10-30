package app.wooportal.server.features.organisation.base;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.Authenticated;
import app.wooportal.server.features.organisation.authorization.permissions.OrganisationAdminPermission;
import app.wooportal.server.features.organisation.authorization.permissions.OrganisationManagePermission;
import app.wooportal.server.features.organisation.comment.OrganisationCommentEntity;
import app.wooportal.server.features.organisation.comment.OrganisationCommentService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class OrganisationApi extends CrudApi<OrganisationEntity, OrganisationService> {

  private final OrganisationCommentService commentService;

  public OrganisationApi(OrganisationService service,
      OrganisationCommentService commentService) {
    super(service);

    this.commentService = commentService;
  }

  @Override
  @GraphQLQuery(name = "getOrganisations")
  public PageableList<OrganisationEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getOrganisation")
  public Optional<OrganisationEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) OrganisationEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveOrganisations")
  @Authenticated
  public List<OrganisationEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<OrganisationEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveOrganisation")
  @Authenticated
  public OrganisationEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) OrganisationEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganisations")
  @OrganisationAdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganisation")
  @OrganisationManagePermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLQuery(name = "lastOrganisationComment")
  public Optional<OrganisationCommentEntity> getLastComment(
      @GraphQLContext OrganisationEntity organisation) {
    return commentService.getMostRecentByOrganisation(organisation.getId());
  }

  @GraphQLMutation(name = "sponsorOrganisation")
  @OrganisationAdminPermission
  public Boolean sponsorContest(String organisationId) {
    return service.sponsor(organisationId);
  }
  
  @GraphQLMutation(name = "changeOrganisationApproval")
  public Boolean changeOrganisationApproval(String organisationId) {
    return service.changeApproval(organisationId);
  }
}
