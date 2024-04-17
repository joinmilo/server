package app.milo.server.features.event.category;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface EventCategoryRepository extends DataRepository<EventCategoryEntity> {

}
