package app.milo.server.base.report.type;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class ReportTypeService extends DataService<ReportTypeEntity, ReportTypePredicateBuilder> {

  public ReportTypeService(DataRepository<ReportTypeEntity> repo,
      ReportTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
