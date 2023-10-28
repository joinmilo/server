package app.wooportal.server.core.security.components.user.passwordReset;

import org.springframework.stereotype.Repository;

import app.wooportal.server.core.repository.DataRepository;

@Repository
interface PasswordResetRepository extends DataRepository<PasswordResetEntity> {

}
