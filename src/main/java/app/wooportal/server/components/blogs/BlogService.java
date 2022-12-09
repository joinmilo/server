package app.wooportal.server.components.blogs;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class BlogService extends DataService<BlogEntity, BlogPredicateBuilder> {

  public BlogService(DataRepository<BlogEntity> repo, BlogPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
