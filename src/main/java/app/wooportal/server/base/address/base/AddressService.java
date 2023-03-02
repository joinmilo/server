package app.wooportal.server.base.address.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AddressService extends DataService<AddressEntity, AddressPredicateBuilder> {

  public AddressService(DataRepository<AddressEntity> repo, AddressPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
