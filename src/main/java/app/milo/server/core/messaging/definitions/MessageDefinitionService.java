package app.milo.server.core.messaging.definitions;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;

@Service
public class MessageDefinitionService
    extends DataService<MessageDefinitionEntity, MessageDefinitionPredicateBuilder> {

  public MessageDefinitionService(MessageDefinitionRepository repo,
      MessageDefinitionPredicateBuilder entities) throws Exception {
    super(repo, entities);

  }
}
