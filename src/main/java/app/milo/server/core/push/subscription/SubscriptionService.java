package app.milo.server.core.push.subscription;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class SubscriptionService
    extends DataService<SubscriptionEntity, SubscriptionPredicateBuilder> {

  public SubscriptionService(DataRepository<SubscriptionEntity> repo,
      SubscriptionPredicateBuilder predicate) {
    super(repo, predicate);
  }

  @Override
  public Optional<SubscriptionEntity> getExisting(SubscriptionEntity entity) {
    return entity.getDeviceToken() == null || entity.getDeviceToken().isEmpty() ? Optional.empty()
        : getByDeviceToken(entity.getDeviceToken());
  }

  public Optional<SubscriptionEntity> getByDeviceToken(String key) {
    return repo.findOne(singleQuery(predicate.withDeviceToken(key)));
  }

  public List<SubscriptionEntity> getAllSubscriptions(String... graph) {
    return repo.findAll(collectionQuery(false).addGraph(graph(graph))).getList();
  }
}
