package app.milo.server.core.push.subscription;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
interface SubscriptionRepository extends DataRepository<SubscriptionEntity> {

}
