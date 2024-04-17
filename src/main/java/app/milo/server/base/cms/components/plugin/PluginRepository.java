package app.milo.server.base.cms.components.plugin;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface PluginRepository extends DataRepository<PluginEntity> {

}
