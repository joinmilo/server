package app.milo.server.features.survey.questionOption;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class SurveyQuestionOptionService extends DataService<SurveyQuestionOptionEntity, SurveyQuestionOptionPredicateBuilder> {

  public SurveyQuestionOptionService(DataRepository<SurveyQuestionOptionEntity> repo, SurveyQuestionOptionPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
