package app.milo.server.features.contest.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface ContestRepository extends DataRepository<ContestEntity> {

}
