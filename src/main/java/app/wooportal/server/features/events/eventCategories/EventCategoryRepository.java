package app.wooportal.server.features.events.eventCategories;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCategoryRepository extends DataRepository<EventCategoryEntity> {

}
