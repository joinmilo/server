package app.wooportal.server.core.visit.visitor;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface VisitorRepository extends DataRepository<VisitorEntity> {

}
