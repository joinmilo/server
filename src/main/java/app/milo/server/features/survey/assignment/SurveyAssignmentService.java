package app.milo.server.features.survey.assignment;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class SurveyAssignmentService
    extends DataService<SurveyAssignmentEntity, SurveyAssignmentPredicateBuilder> {

  public SurveyAssignmentService(DataRepository<SurveyAssignmentEntity> repo,
      SurveyAssignmentPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
