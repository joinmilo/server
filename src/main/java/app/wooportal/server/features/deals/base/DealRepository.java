package app.wooportal.server.features.deals.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends DataRepository<DealEntity> {

}
