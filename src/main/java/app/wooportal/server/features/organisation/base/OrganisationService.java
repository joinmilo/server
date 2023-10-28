package app.wooportal.server.features.organisation.base;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import app.wooportal.server.base.address.base.AddressService;
import app.wooportal.server.base.contact.ContactService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.organisation.media.OrganisationMediaService;

@Service
public class OrganisationService extends DataService<OrganisationEntity, OrganisationPredicateBuilder> {

  public OrganisationService(
      DataRepository<OrganisationEntity> repo,
      OrganisationPredicateBuilder predicate,
      AddressService addressService,
      ContactService contactService,
      OrganisationMediaService uploadService) {
    super(repo, predicate);
    
    addService("address", addressService);
    addService("contact", contactService);
    addService("uploads", uploadService);
  }
  
  @Override
  public void preCreate(OrganisationEntity entity, OrganisationEntity newEntity, JsonNode context) {
    newEntity.setSponsored(true);
    addContext("sponsored", context);
  }    
  
  public Boolean sponsor(String rganisationId) {
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
