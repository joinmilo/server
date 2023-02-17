package app.wooportal.server.features.surveys.state;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SurveyStateService extends DataService<SurveyStateEntity, SurveyStatePredicateBuilder> {

  public SurveyStateService(DataRepository<SurveyStateEntity> repo, SurveyStatePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
