package app.wooportal.server.features.survey.answer;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AnswerService extends DataService<AnswerEntity, AnswerPredicateBuilder> {

  public AnswerService(DataRepository<AnswerEntity> repo, AnswerPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
