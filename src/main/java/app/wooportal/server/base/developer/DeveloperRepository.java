package app.wooportal.server.base.developer;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface DeveloperRepository extends DataRepository<DeveloperEntity> {

}
