package app.milo.server.base.report.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface ReportRepository extends DataRepository<ReportEntity> {

}
