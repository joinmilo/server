package app.wooportal.server.features.organisations.members;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends DataRepository<MemberEntity> {

}
