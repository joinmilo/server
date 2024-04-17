package app.milo.server.core.security.components.user.emailVerification;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
interface VerificationRepository extends DataRepository<VerificationEntity> {

}
