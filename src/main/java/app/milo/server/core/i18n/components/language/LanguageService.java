package app.milo.server.core.i18n.components.language;

import org.springframework.stereotype.Service;
import app.milo.server.base.configuration.ConfigurationService;
import app.milo.server.core.base.DataService;
import app.milo.server.core.i18n.TranslationsConfiguration;
import app.milo.server.core.repository.DataRepository;

@Service
public class LanguageService extends DataService<LanguageEntity, LanguagePredicateBuilder> {
  
  public LanguageService(
      DataRepository<LanguageEntity> repo,
      LanguagePredicateBuilder predicate,
      ConfigurationService configurationService,
      TranslationsConfiguration staticConfig) {
    super(repo, predicate);
  }

}
