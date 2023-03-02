package app.wooportal.server.features.event.base.visitors;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.visit.visitable.VisitableRepository;

@Repository
public interface EventVisitorRepository extends VisitableRepository<EventVisitorEntity> {

}
