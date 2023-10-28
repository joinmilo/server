package app.wooportal.server.base.configuration;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;

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
