package app.milo.server.features.survey.answer;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class SurveyAnswerService extends DataService<SurveyAnswerEntity, SurveyAnswerPredicateBuilder> {

  public SurveyAnswerService(DataRepository<SurveyAnswerEntity> repo, SurveyAnswerPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
