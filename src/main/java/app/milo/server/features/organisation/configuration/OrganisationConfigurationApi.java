package app.milo.server.features.organisation.configuration;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.milo.server.core.base.CrudApi;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.listing.PageableList;
import app.milo.server.features.organisation.authorization.permissions.OrganisationAdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class OrganisationConfigurationApi extends CrudApi<OrganisationConfigurationEntity, OrganisationConfigurationService> {

  public OrganisationConfigurationApi(OrganisationConfigurationService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getOrganisationConfigurations")
  @OrganisationAdminPermission
  public PageableList<OrganisationConfigurationEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getOrganisationConfiguration")
  @OrganisationAdminPermission
  public Optional<OrganisationConfigurationEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) OrganisationConfigurationEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveOrganisationConfigurations")
  @OrganisationAdminPermission
  public List<OrganisationConfigurationEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<OrganisationConfigurationEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveOrganisationConfiguration")
  @OrganisationAdminPermission
  public OrganisationConfigurationEntity saveOne(@GraphQLArgument(name = CrudApi.entity) OrganisationConfigurationEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganisationConfigurations")
  @OrganisationAdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganisationConfiguration")
  @OrganisationAdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
