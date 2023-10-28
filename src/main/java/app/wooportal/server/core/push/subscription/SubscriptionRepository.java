package app.wooportal.server.core.push.subscription;

import org.springframework.stereotype.Repository;

import app.wooportal.server.core.repository.DataRepository;

@Repository
interface SubscriptionRepository extends DataRepository<SubscriptionEntity> {

}
