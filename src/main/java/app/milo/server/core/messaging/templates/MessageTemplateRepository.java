package app.milo.server.core.messaging.templates;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface MessageTemplateRepository extends DataRepository<MessageTemplateEntity> {

  
}
