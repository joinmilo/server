package app.milo.server.core.messaging.notifications.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
interface NotificationRepository extends DataRepository<NotificationEntity> {

}
