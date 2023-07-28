package app.wooportal.server.features.survey.assignment;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SurveyAssignmentService
    extends DataService<SurveyAssignmentEntity, SurveyAssignmentPredicateBuilder> {

  public SurveyAssignmentService(DataRepository<SurveyAssignmentEntity> repo,
      SurveyAssignmentPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
