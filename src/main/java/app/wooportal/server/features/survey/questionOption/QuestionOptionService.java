package app.wooportal.server.features.survey.questionOption;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class QuestionOptionService extends DataService<QuestionOptionEntity, QuestionOptionPredicateBuilder> {

  public QuestionOptionService(DataRepository<QuestionOptionEntity> repo, QuestionOptionPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
