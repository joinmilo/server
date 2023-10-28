package app.wooportal.server.features.contest.comment;

import java.util.Optional;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.utils.SortPageUtils;

@Service
public class ContestCommentService
    extends DataService<ContestCommentEntity, ContestCommentPredicateBuilder> {

  public ContestCommentService(DataRepository<ContestCommentEntity> repo,
      ContestCommentPredicateBuilder predicate) {
    super(repo, predicate);
  }
  
  public Optional<ContestCommentEntity> getMostRecentByContest(String contestId) {
    var result = repo.findAll(
        collectionQuery(predicate.withContestId(contestId))
          .setLimit(1)
          .setSort(SortPageUtils.createSort(Direction.DESC, "modified")));
    
    return result != null && !result.isEmpty()
        ? Optional.ofNullable(result.get(0))
        : Optional.empty();
  }
}
