package app.wooportal.server.core.i18n.components.label;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class LabelService extends DataService<LabelEntity, LabelPredicateBuilder> {

  public LabelService(DataRepository<LabelEntity> repo, LabelPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
