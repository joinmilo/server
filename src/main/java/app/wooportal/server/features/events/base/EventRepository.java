package app.wooportal.server.features.events.base;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface EventRepository extends DataRepository<EventEntity> {

}
