package app.wooportal.server.core.messaging.notifications.channel;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
interface ChannelRepository extends DataRepository<ChannelEntity> {

}
