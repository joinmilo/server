package app.wooportal.server.base.external.app;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface AppsRepository extends DataRepository<AppEntity> {

}
