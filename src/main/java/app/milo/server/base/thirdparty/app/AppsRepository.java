package app.milo.server.base.thirdparty.app;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface AppsRepository extends DataRepository<AppEntity> {

}
