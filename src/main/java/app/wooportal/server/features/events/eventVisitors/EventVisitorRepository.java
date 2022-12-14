package app.wooportal.server.features.events.eventVisitors;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventVisitorRepository extends DataRepository<EventVisitorEntity> {

}
