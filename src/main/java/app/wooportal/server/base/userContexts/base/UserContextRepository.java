package app.wooportal.server.base.userContexts.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContextRepository extends DataRepository<UserContextEntity> {

}
