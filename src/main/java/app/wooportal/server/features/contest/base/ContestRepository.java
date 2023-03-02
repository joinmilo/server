package app.wooportal.server.features.contest.base;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface ContestRepository extends DataRepository<ContestEntity> {

}
