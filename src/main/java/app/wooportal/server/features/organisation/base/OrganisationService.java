package app.wooportal.server.features.organisation.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class OrganisationService extends DataService<OrganisationEntity, OrganisationPredicateBuilder> {

  public OrganisationService(DataRepository<OrganisationEntity> repo, OrganisationPredicateBuilder predicate) {
    super(repo, predicate);
  }
  
  public Boolean sponsorOrganisation(String rganisationId) {
    var rganisation = getById(rganisationId);
    
    if (rganisation.isPresent()) {
      rganisation.get().setSponsored(true);
      repo.save(rganisation.get());
      
      unsponsorOthers(rganisationId);
      
      //TODO: Send notifications
      
      return true;
    }
    return false;
  }

  private void unsponsorOthers(String rganisationId) {
    var others = readAll(collectionQuery(predicate.withoutId(rganisationId)));
    if (others != null && !others.isEmpty()) {
      others.getList().stream().forEach(rganisation -> {
        rganisation.setSponsored(false);
        repo.save(rganisation);
      });
    }
  }
}
