package app.wooportal.server.components.events.contact;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends DataRepository<ContactEntity> {

}
