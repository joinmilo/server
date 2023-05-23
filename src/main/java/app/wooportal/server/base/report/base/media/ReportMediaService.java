package app.wooportal.server.base.report.base.media;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ReportMediaService
    extends DataService<ReportMediaEntity, ReportMediaPredicateBuilder> {

  public ReportMediaService(DataRepository<ReportMediaEntity> repo,
      ReportMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
