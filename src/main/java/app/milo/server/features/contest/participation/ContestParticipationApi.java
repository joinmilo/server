package app.milo.server.features.contest.participation;

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
public class ContestParticipationApi
    extends CrudApi<ContestParticipationEntity, ContestParticipationService> {


  public ContestParticipationApi(ContestParticipationService service) {
    super(service);

  }

  @Override
  @GraphQLQuery(name = "getContestParticipations")
  public PageableList<ContestParticipationEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getContesParticipation")
  public Optional<ContestParticipationEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ContestParticipationEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveContestParticipations")
  public List<ContestParticipationEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ContestParticipationEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveContestParticipation")
  public ContestParticipationEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) ContestParticipationEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteContestParticipations")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteContestParticipation")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
