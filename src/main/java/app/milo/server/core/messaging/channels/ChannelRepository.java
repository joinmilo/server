package app.milo.server.core.messaging.channels;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
interface ChannelRepository extends DataRepository<ChannelEntity> {

}
