package app.wooportal.server.base.address.suburb;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SuburbService extends DataService<SuburbEntity, SuburbPredicateBuilder> {

  public SuburbService(DataRepository<SuburbEntity> repo, SuburbPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
