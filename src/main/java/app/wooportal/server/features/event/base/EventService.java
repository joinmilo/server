package app.wooportal.server.features.event.base;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.event.attendeeConfiguration.EventAttendeeConfigurationService;

@Service
public class EventService extends DataService<EventEntity, EventPredicateBuilder> {

  public EventService(DataRepository<EventEntity> repo, EventPredicateBuilder predicate,
      @Lazy EventAttendeeConfigurationService attendeeConfigurationService) {
    super(repo, predicate);

    addService("attendeeConfiguration", attendeeConfigurationService);
   
  }
  
  public Boolean sponsorEvent(String eventId) {
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
}
