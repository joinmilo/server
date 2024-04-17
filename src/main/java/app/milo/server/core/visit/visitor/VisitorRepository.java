package app.milo.server.core.visit.visitor;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface VisitorRepository extends DataRepository<VisitorEntity> {

}
