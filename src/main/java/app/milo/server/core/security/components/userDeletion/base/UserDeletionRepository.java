package app.milo.server.core.security.components.userDeletion.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface UserDeletionRepository extends DataRepository<UserDeletionEntity> {

}
