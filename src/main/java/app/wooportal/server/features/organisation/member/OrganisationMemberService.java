package app.wooportal.server.features.organisation.member;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class OrganisationMemberService extends DataService<OrganisationMemberEntity, OrganisationMemberPredicateBuilder> {

  public OrganisationMemberService(DataRepository<OrganisationMemberEntity> repo, OrganisationMemberPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
