package app.wooportal.server.base.newsFeed;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class NewsFeedService extends DataService<NewsFeedEntity, NewsFeedPredicateBuilder> {

  public NewsFeedService(DataRepository<NewsFeedEntity> repo, NewsFeedPredicateBuilder predicate) {
    super(repo, predicate);
  }
}


