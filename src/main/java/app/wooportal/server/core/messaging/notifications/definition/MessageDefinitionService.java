package app.wooportal.server.core.messaging.notifications.definition;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;

@Service
public class MessageDefinitionService
    extends DataService<MessageDefinitionEntity, MessageDefinitionPredicateBuilder> {

  public MessageDefinitionService(MessageDefinitionRepository repo,
      MessageDefinitionPredicateBuilder entities) throws Exception {
    super(repo, entities);

  }
}
