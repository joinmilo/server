package app.milo.server.features.event.comment;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface EventCommentRepository extends DataRepository<EventCommentEntity> {

}
