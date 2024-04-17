package app.milo.server.base.userContext.friend;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface FriendRepository extends DataRepository<FriendEntity> {

}
