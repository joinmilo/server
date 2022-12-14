package app.wooportal.server.features.surveys.questions;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class QuestionService extends DataService<QuestionEntity, QuestionPredicateBuilder> {

  public QuestionService(DataRepository<QuestionEntity> repo, QuestionPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
