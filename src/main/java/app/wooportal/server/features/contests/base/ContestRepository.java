package app.wooportal.server.features.contests.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends DataRepository<ContestEntity> {

}
