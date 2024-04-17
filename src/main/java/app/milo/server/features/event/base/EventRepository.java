package app.milo.server.features.event.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface EventRepository extends DataRepository<EventEntity> {

}
