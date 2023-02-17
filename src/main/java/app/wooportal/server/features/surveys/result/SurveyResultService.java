package app.wooportal.server.features.surveys.result;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SurveyResultService extends DataService<SurveyResultEntity, SurveyResultPredicateBuilder> {

  public SurveyResultService(DataRepository<SurveyResultEntity> repo, SurveyResultPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
