package app.wooportal.server.components.feedbacks.comment;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends DataRepository<CommentEntity> {

}
