package app.milo.server.features.organisation.media;

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
public class OrganisationMediaApi extends CrudApi<OrganisationMediaEntity, OrganisationMediaService> {

  public OrganisationMediaApi(OrganisationMediaService service) {
    super(service);
  }
  
  @Override
  @GraphQLQuery(name = "getOrganisationMedia")
  public PageableList<OrganisationMediaEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getOrganisationMedium")
  public Optional<OrganisationMediaEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) OrganisationMediaEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveOrganisationMedia")
  public List<OrganisationMediaEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<OrganisationMediaEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveOrganisationMedium")
  public OrganisationMediaEntity saveOne(@GraphQLArgument(name = CrudApi.entity) OrganisationMediaEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganisationMedia")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganisationMedium")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
