package app.wooportal.server.features.contest.vote;

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
public class ContestVoteApi extends CrudApi<ContestVoteEntity, ContestVoteService> {

  public ContestVoteApi(ContestVoteService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getContestVotes")
  public PageableList<ContestVoteEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getContestVote")
  public Optional<ContestVoteEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ContestVoteEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveContestVotes")
  public List<ContestVoteEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ContestVoteEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveContestVote")
  public ContestVoteEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) ContestVoteEntity entity) {
    return super.saveOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "deleteContestVotes")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteContestVote")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
