package app.milo.server.features.contest.base;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.milo.server.core.base.CrudApi;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.listing.PageableList;
import app.milo.server.core.security.permissions.AdminPermission;
import app.milo.server.features.contest.comment.ContestCommentEntity;
import app.milo.server.features.contest.comment.ContestCommentService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ContestApi extends CrudApi<ContestEntity, ContestService> {

  private final ContestCommentService commentService;
  
  public ContestApi(
      ContestService service,
      ContestCommentService commentService) {
    super(service);
    
    this.commentService = commentService;
  }

  @Override
  @GraphQLQuery(name = "getContests")
  public PageableList<ContestEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getContest")
  public Optional<ContestEntity> readOne(@GraphQLArgument(name = CrudApi.entity) ContestEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveContests")
  @AdminPermission
  public List<ContestEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ContestEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveContest")
  @AdminPermission
  public ContestEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ContestEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteContests")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteContest")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLQuery(name = "lastContestComment")
  public Optional<ContestCommentEntity> getLastComment(
      @GraphQLContext ContestEntity contest) {
    return commentService.getMostRecentByContest(contest.getId());
  }
  
  @GraphQLMutation(name = "sponsorContest")
  @AdminPermission
  public Boolean sponsorContest(String contestId) {
    return service.sponsorContest(contestId);
  }
}
