package app.wooportal.server.base.address.base;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface AddressRepository extends DataRepository<AddressEntity> {

}
