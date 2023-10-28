package app.wooportal.server.features.infoMedia.category;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class InfoMediaCategoryService extends DataService<InfoMediaCategoryEntity, InfoMediaCategoryPredicateBuilder> {

  public InfoMediaCategoryService(
      DataRepository<InfoMediaCategoryEntity> repo,
      InfoMediaCategoryPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
