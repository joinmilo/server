package app.wooportal.server.core.messaging.templates;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;

@Service
public class MessageTemplateService
    extends DataService<MessageTemplateEntity, MessageTemplatePredicateBuilder> {

  public MessageTemplateService(MessageTemplateRepository repo,
      MessageTemplatePredicateBuilder entities) {
    super(repo, entities);
  }

}
