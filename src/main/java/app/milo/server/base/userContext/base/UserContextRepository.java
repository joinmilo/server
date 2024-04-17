package app.milo.server.base.userContext.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface UserContextRepository extends DataRepository<UserContextEntity> {

}
