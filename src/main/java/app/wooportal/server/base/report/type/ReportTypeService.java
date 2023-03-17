package app.wooportal.server.base.report.type;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ReportTypeService extends DataService<ReportTypeEntity, ReportTypePredicateBuilder> {

  public ReportTypeService(DataRepository<ReportTypeEntity> repo,
      ReportTypePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
