package app.wooportal.server.features.contests.votes;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface ContestVoteRepository extends DataRepository<ContestVoteEntity> {

}
