package app.wooportal.server.features.organisation.member;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class MemberService extends DataService<MemberEntity, MemberPredicateBuilder> {

  public MemberService(DataRepository<MemberEntity> repo, MemberPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
