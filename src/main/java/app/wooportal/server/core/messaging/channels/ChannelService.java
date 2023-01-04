package app.wooportal.server.core.messaging.channels;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;

@Service
public class ChannelService extends DataService<ChannelEntity, ChannelPredicateBuilder> {

  public ChannelService(ChannelRepository repo, ChannelPredicateBuilder entities) throws Exception {
    super(repo, entities);

  }

}
