package app.milo.server.features.contest.participation;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface ContestParticipationRepository extends DataRepository<ContestParticipationEntity> {

}
