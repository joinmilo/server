package app.wooportal.server.base.feedback.type;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class FeedbackTypeService extends DataService<FeedbackTypeEntity, FeedbackTypePredicateBuilder> {

  public FeedbackTypeService(DataRepository<FeedbackTypeEntity> repo,
      FeedbackTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
