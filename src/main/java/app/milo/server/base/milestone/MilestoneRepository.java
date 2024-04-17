package app.milo.server.base.milestone;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface MilestoneRepository extends DataRepository<MilestoneEntity> {

}
