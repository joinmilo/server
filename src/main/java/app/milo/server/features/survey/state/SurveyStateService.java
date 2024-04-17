package app.milo.server.features.survey.state;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class SurveyStateService extends DataService<SurveyStateEntity, SurveyStatePredicateBuilder> {

  public SurveyStateService(DataRepository<SurveyStateEntity> repo, SurveyStatePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
