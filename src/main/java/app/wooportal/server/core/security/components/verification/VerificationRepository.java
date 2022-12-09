package app.wooportal.server.core.security.components.verification;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface VerificationRepository extends DataRepository<VerificationEntity> {

}
