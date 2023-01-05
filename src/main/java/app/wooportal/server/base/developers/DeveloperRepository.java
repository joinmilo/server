package app.wooportal.server.base.developers;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends DataRepository<DeveloperEntity> {

}
