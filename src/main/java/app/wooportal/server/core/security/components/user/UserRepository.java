package app.wooportal.server.core.security.components.user;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends DataRepository<UserEntity> {

}
