package app.wooportal.server.core.error.errorMessage;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface ErrorMessageRepository extends DataRepository<ErrorMessageEntity> {

}
