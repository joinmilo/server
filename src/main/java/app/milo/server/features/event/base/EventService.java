package app.milo.server.features.event.base;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.milo.server.base.address.base.AddressService;
import app.milo.server.base.contact.ContactService;
import app.milo.server.base.userContext.security.UserContextAuthorizationService;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.features.event.attendeeConfiguration.EventAttendeeConfigurationService;
import app.milo.server.features.event.media.EventMediaService;
import app.milo.server.features.event.schedule.EventScheduleService;

@Service
public class EventService extends DataService<EventEntity, EventPredicateBuilder> {
  
  private UserContextAuthorizationService authService;
  
  public EventService(DataRepository<EventEntity> repo,
      EventPredicateBuilder predicate,
      UserContextAuthorizationService authService,
      EventAttendeeConfigurationService attendeeConfigurationService,
      AddressService addressService,
      EventScheduleService scheduleService,
      EventMediaService eventMediaService,
      ContactService contactService) {
    super(repo, predicate);
    
    this.authService = authService;

    addService("attendeeConfiguration", attendeeConfigurationService);
    addService("address", addressService);
    addService("schedules", scheduleService);
    addService("uploads", eventMediaService);
    addService("contact", contactService);
   
  }
  
  @Override
  public void preCreate(EventEntity entity, EventEntity newEntity, JsonNode context) {
    newEntity.setSponsored(false);
    addContext("sponsored", context);
    
    var currentUser = authService.getAuthenticatedUserContext();
    if (currentUser.isPresent()) {
      newEntity.setCreator(currentUser.get());
      addContext("creator", context);
    }
  }
  
  public Boolean sponsor(String eventId) {
    var event = getById(eventId);
    
    if (event.isPresent()) {
      event.get().setSponsored(true);
      repo.save(event.get());
      
      unsponsorOther(eventId);
      
      //TODO: Send notifications
      
      return true;
    }
    return false;
  }

  private void unsponsorOther(String eventId) {
	var other = readAll(collectionQuery(predicate.withSponsoredTrue().and(predicate.withoutId(eventId))));
    if (other != null && !other.isEmpty()) {
      other.getList().stream().forEach(event -> {
        event.setSponsored(false);
        repo.save(event);
      });
    }
  }  
}
