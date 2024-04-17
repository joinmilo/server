package app.milo.server.core.security.components.userDeletion.type;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface UserDeletionTypeRepository extends DataRepository<UserDeletionTypeEntity> {

}
