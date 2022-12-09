package app.wooportal.server.components.bloggers;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class BloggerService extends DataService<BloggerEntity, BloggerPredicateBuilder> {

  public BloggerService(DataRepository<BloggerEntity> repo, BloggerPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
