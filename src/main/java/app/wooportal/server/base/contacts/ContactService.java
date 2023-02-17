package app.wooportal.server.base.contacts;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ContactService extends DataService<ContactEntity, ContactPredicateBuilder> {

  public ContactService(DataRepository<ContactEntity> repo, ContactPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
