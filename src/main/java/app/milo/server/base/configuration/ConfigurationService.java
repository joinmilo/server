package app.milo.server.base.configuration;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.media.base.MediaService;
import app.milo.server.core.repository.DataRepository;

@Service
public class ConfigurationService
    extends DataService<ConfigurationEntity, ConfigurationPredicateBuilder> {

  public ConfigurationService(
      DataRepository<ConfigurationEntity> repo,
      ConfigurationPredicateBuilder predicate,
      MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);
  }
}
