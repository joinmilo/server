package app.wooportal.server.features.event.base;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.base.address.base.AddressService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.event.attendeeConfiguration.EventAttendeeConfigurationService;
import app.wooportal.server.features.event.media.EventMediaService;
import app.wooportal.server.features.event.schedule.EventScheduleService;

@Service
public class EventService extends DataService<EventEntity, EventPredicateBuilder> {

  public EventService(DataRepository<EventEntity> repo,
      EventPredicateBuilder predicate,
      EventAttendeeConfigurationService attendeeConfigurationService,
      AddressService addressService,
      EventScheduleService scheduleService,
      EventMediaService eventMediaService) {
    super(repo, predicate);

    addService("attendeeConfiguration", attendeeConfigurationService);
    addService("address", addressService);
    addService("schedules", scheduleService);
    addService("uploads", eventMediaService);
   
  }
  
  public Boolean sponsor(String eventId) {
    var event = getById(eventId);
    
    if (event.isPresent()) {
      event.get().setSponsored(true);
      repo.save(event.get());
      
      unsponsorOthers(eventId);
      
      //TODO: Send notifications
      
      return true;
    }
    return false;
  }

  private void unsponsorOthers(String eventId) {
    var others = readAll(collectionQuery(predicate.withoutId(eventId)));
    if (others != null && !others.isEmpty()) {
      others.getList().stream().forEach(event -> {
        event.setSponsored(false);
        repo.save(event);
      });
    }
  }
  
  @Override
  public void preCreate(EventEntity entity, EventEntity newEntity, JsonNode context) {
     newEntity.setSponsored(false);
     addContext("sponsored", context);
  }
}
