package app.wooportal.server.features.contest.comment;

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
public class ContestCommentApi extends CrudApi<ContestCommentEntity, ContestCommentService> {

  public ContestCommentApi(ContestCommentService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getContestComments")
  public PageableList<ContestCommentEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getContestComment")
  public Optional<ContestCommentEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ContestCommentEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveContestComments")
  public List<ContestCommentEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ContestCommentEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveContestComment")
  public ContestCommentEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ContestCommentEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteContestComments")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteContestComment")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
