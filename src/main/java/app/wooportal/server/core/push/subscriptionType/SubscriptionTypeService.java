package app.wooportal.server.core.push.subscriptionType;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SubscriptionTypeService
    extends DataService<SubscriptionTypeEntity, SubscriptionTypePredicateBuilder> {

  public SubscriptionTypeService(DataRepository<SubscriptionTypeEntity> repo,
      SubscriptionTypePredicateBuilder predicate) {
    super(repo, predicate);

  }

  @Override
  public Optional<SubscriptionTypeEntity> getExisting(SubscriptionTypeEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<SubscriptionTypeEntity> getByName(String name) {
    return repo.findOne(singleQuery(predicate.withName(name)));
  }
}

