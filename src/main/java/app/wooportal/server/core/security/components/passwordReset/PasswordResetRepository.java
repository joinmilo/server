package app.wooportal.server.core.security.components.passwordReset;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PasswordResetRepository extends DataRepository<PasswordResetEntity> {

}
