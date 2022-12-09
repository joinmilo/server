package app.wooportal.server.core.messaging.notifications.base;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
interface NotificationRepository extends DataRepository<NotificationEntity> {

}
