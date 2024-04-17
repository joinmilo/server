package app.milo.server.features.contest.comment;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface ContestCommentRepository extends DataRepository<ContestCommentEntity> {

}
