package app.milo.server.base.address.suburb;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class SuburbService extends DataService<SuburbEntity, SuburbPredicateBuilder> {

  public SuburbService(DataRepository<SuburbEntity> repo, SuburbPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
