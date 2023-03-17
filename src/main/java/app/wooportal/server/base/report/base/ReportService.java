package app.wooportal.server.base.report.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ReportService extends DataService<ReportEntity, ReportPredicateBuilder> {

  public ReportService(DataRepository<ReportEntity> repo,
      ReportPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
