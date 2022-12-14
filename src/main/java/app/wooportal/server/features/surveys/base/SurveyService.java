package app.wooportal.server.features.surveys.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SurveyService extends DataService<SurveyEntity, SurveyPredicateBuilder> {

  public SurveyService(DataRepository<SurveyEntity> repo, SurveyPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
