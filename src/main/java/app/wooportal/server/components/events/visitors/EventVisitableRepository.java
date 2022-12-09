package app.wooportal.server.components.events.visitors;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.visit.visitable.VisitableRepository;

@Repository
public interface EventVisitableRepository extends VisitableRepository<EventVisitorEntity> {
}
