package app.wooportal.server.features.survey.answer;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SurveyAnswerService extends DataService<SurveyAnswerEntity, SurveyAnswerPredicateBuilder> {

  public SurveyAnswerService(DataRepository<SurveyAnswerEntity> repo, SurveyAnswerPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
