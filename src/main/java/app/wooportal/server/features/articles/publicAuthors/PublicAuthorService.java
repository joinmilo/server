package app.wooportal.server.features.articles.publicAuthors;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PublicAuthorService extends DataService<PublicAuthorEntity, PublicAuthorPredicateBuilder> {

  public PublicAuthorService(DataRepository<PublicAuthorEntity> repo, PublicAuthorPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
