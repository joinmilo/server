package app.milo.server.features.survey.result;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class SurveyResultService extends DataService<SurveyResultEntity, SurveyResultPredicateBuilder> {

  public SurveyResultService(DataRepository<SurveyResultEntity> repo, SurveyResultPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
