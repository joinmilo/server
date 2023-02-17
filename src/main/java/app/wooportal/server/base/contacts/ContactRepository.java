package app.wooportal.server.base.contacts;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface ContactRepository extends DataRepository<ContactEntity> {

}
