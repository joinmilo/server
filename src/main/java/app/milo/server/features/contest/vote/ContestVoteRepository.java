package app.milo.server.features.contest.vote;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface ContestVoteRepository extends DataRepository<ContestVoteEntity> {

}
