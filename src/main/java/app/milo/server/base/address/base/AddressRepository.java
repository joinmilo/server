package app.milo.server.base.address.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface AddressRepository extends DataRepository<AddressEntity> {

}
