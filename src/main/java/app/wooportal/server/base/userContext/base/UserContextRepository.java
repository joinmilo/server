package app.wooportal.server.base.userContext.base;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface UserContextRepository extends DataRepository<UserContextEntity> {

}
