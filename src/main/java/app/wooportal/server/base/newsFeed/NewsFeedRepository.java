package app.wooportal.server.base.newsFeed;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface NewsFeedRepository extends DataRepository<NewsFeedEntity> {

}
