package app.milo.server.core.messaging.channels;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;

@Service
public class ChannelService extends DataService<ChannelEntity, ChannelPredicateBuilder> {

  public ChannelService(ChannelRepository repo, ChannelPredicateBuilder entities) throws Exception {
    super(repo, entities);

  }

}
