package app.milo.server.base.configuration;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface ConfigurationRepository extends DataRepository<ConfigurationEntity> {

}
