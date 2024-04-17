package app.milo.server.base.contact;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface ContactRepository extends DataRepository<ContactEntity> {

}
