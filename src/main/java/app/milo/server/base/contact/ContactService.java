package app.milo.server.base.contact;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class ContactService extends DataService<ContactEntity, ContactPredicateBuilder> {

  public ContactService(DataRepository<ContactEntity> repo, ContactPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
