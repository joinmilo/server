package app.wooportal.server.base.feedback.base;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface FeedbackRepository extends DataRepository<FeedbackEntity> {

}
