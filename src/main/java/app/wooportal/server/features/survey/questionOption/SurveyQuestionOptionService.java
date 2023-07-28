package app.wooportal.server.features.survey.questionOption;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SurveyQuestionOptionService extends DataService<SurveyQuestionOptionEntity, SurveyQuestionOptionPredicateBuilder> {

  public SurveyQuestionOptionService(DataRepository<SurveyQuestionOptionEntity> repo, SurveyQuestionOptionPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
