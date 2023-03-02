package app.wooportal.server.features.survey.questionType;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class QuestionTypeService extends DataService<QuestionTypeEntity, QuestionTypePredicateBuilder> {

  public QuestionTypeService(DataRepository<QuestionTypeEntity> repo, QuestionTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
