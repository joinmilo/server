package app.wooportal.server.core.security.components.user.emailVerification;

import org.springframework.stereotype.Repository;

import app.wooportal.server.core.repository.DataRepository;

@Repository
interface VerificationRepository extends DataRepository<VerificationEntity> {

}
