package app.milo.server.core.messaging.definitions;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
interface MessageDefinitionRepository extends DataRepository<MessageDefinitionEntity> {

}
