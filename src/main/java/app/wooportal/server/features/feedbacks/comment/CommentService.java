package app.wooportal.server.features.feedbacks.comment;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class CommentService extends DataService<CommentEntity, CommentPredicateBuilder> {

  public CommentService(DataRepository<CommentEntity> repo, CommentPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
