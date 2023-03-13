package app.wooportal.server.base.configuration;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface ConfigurationRepository extends DataRepository<ConfigurationEntity> {

}
