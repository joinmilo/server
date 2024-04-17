package app.milo.server.core.error.errorMessage;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface ErrorMessageRepository extends DataRepository<ErrorMessageEntity> {

}
