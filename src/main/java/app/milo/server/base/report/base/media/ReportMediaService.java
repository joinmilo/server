package app.milo.server.base.report.base.media;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class ReportMediaService
    extends DataService<ReportMediaEntity, ReportMediaPredicateBuilder> {

  public ReportMediaService(DataRepository<ReportMediaEntity> repo,
      ReportMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
