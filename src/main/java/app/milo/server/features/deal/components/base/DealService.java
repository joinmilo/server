package app.milo.server.features.deal.components.base;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.milo.server.base.address.base.AddressService;
import app.milo.server.base.contact.ContactService;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.features.deal.components.base.media.DealMediaService;

@Service
public class DealService extends DataService<DealEntity, DealPredicateBuilder> {

  public DealService(
      DataRepository<DealEntity> repo,
      DealPredicateBuilder predicate,
      AddressService addressService,
      ContactService contactService,
      DealMediaService mediaService) {
    super(repo, predicate);

    addService("address", addressService);
    addService("contact", contactService);
    addService("uploads", mediaService);
  }

  @Override
  public void preCreate(DealEntity entity, DealEntity newEntity, JsonNode context) {
    newEntity.setSponsored(false);
    addContext("sponsored", context);
  }

  public Boolean sponsor(String dealId) {
    var Deal = getById(dealId);

    if (Deal.isPresent()) {
      Deal.get().setSponsored(true);
      repo.save(Deal.get());

      unsponsorOther(dealId);

      // TODO: Send notifications

      return true;
    }
    return false;
  }

  private void unsponsorOther(String dealId) {
    var other = readAll(collectionQuery(
        predicate.withSponsoredTrue()
          .and(predicate.withoutId(dealId))));
    if (other != null && !other.isEmpty()) {
      other.getList().stream().forEach(Deal -> {
        Deal.setSponsored(false);
        repo.save(Deal);
      });
    }
  }
}
