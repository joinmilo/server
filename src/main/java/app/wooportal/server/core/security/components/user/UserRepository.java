package app.wooportal.server.core.security.components.user;

import org.springframework.stereotype.Repository;

import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface UserRepository extends DataRepository<UserEntity> {

}
