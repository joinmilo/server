package app.wooportal.server.features.survey.assignment;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AssignmentService
    extends DataService<AssignmentEntity, AssignmentPredicateBuilder> {

  public AssignmentService(DataRepository<AssignmentEntity> repo,
      AssignmentPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
