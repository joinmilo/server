package app.wooportal.server.components.events.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends DataRepository<EventEntity> {

}
