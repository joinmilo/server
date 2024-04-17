package app.milo.server.core.i18n.components.label;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class LabelService extends DataService<LabelEntity, LabelPredicateBuilder> {

  public LabelService(DataRepository<LabelEntity> repo, LabelPredicateBuilder predicate) {
    super(repo, predicate);
  }

  @Override
  public Optional<LabelEntity> getExisting(LabelEntity entity) {
    return entity.getTagId() == null || entity.getTagId().isEmpty()
        ? Optional.empty()
        : getByTagId(entity.getTagId());
  }
  
  public Optional<LabelEntity> getByTagId(String tagId) {
    return repo.findOne(singleQuery(predicate.withTagId(tagId)));
  }
  
}
