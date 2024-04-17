package app.milo.server.features.deal.components.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface DealRepository extends DataRepository<DealEntity> {

}
