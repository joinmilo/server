package app.milo.server.core.security.components.user.passwordReset;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
interface PasswordResetRepository extends DataRepository<PasswordResetEntity> {

}
