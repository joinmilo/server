package app.wooportal.server.core.i18n.components.language;

import java.util.Optional;
import org.springframework.stereotype.Service;

import app.wooportal.server.base.configuration.ConfigurationService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.i18n.TranslationsConfiguration;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.article.components.media.ArticleMediaEntity;

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
