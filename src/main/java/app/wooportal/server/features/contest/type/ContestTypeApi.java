package app.wooportal.server.features.contest.type;

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
public class ContestTypeApi extends CrudApi<ContestTypeEntity, ContestTypeService> {

  public ContestTypeApi(ContestTypeService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getContestTypes")
  public PageableList<ContestTypeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getContestType")
  public Optional<ContestTypeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ContestTypeEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveContestTypes")
  public List<ContestTypeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ContestTypeEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveContestType")
  public ContestTypeEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) ContestTypeEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteContestTypes")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteContestType")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
