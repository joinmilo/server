package app.wooportal.server.features.event.authorization.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import app.wooportal.server.base.userContext.security.UserContextAuthorizationService;
import app.wooportal.server.features.event.base.EventEntity;
import app.wooportal.server.features.event.media.EventMediaService;

@Service
public class EventMediaAuthorizationService {
  
  private final UserContextAuthorizationService authorizationService;
  private final EventMediaService service;
  
  public EventMediaAuthorizationService(
      UserContextAuthorizationService authorizationService,
      EventMediaService service) {
    this.service = service;
    this.authorizationService = authorizationService;
  }
  
  public boolean isOwn(
      Authentication authentication,
      EventEntity entity,
      List<EventEntity> entities,
      String id,
      List<String> ids) {
    var entityIds = getIds(entity, entities, id, ids);
    
    return entityIds != null
        ? verifyOwn(authentication, entityIds) 
        : true;
  }

  private List<String> getIds(
      EventEntity entity,
      List<EventEntity> entities,
      String id,
      List<String> ids) {
    var result = new ArrayList<String>();
    
    if (entity != null && entity.getId() != null && !entity.getId().isBlank()) {
      result.add(entity.getId());
    }
    
    if (entities != null && !entities.isEmpty()) {
      result.addAll(entities.stream()
          .map(entry -> entry.getId())
          .filter(entryId -> entryId != null && !entryId.isBlank())
          .collect((Collectors.toList())));
    }
    
    if (id != null && !id.isBlank()) {
      result.add(id);
    }
    
    if (ids != null && !ids.isEmpty()) {
      result.addAll(ids.stream()
          .filter(entryId -> entryId != null && !entryId.isBlank())
          .collect(Collectors.toList()));
    }
    
    return result;
  }

  private boolean verifyOwn(Authentication authentication, List<String> entityIds) {
    var userContext = authorizationService.getUserContext(authentication);
    if (!userContext.isEmpty() && entityIds != null) {
      var query = service.collectionQuery(false);
      var predicate = service.getPredicate();
      var userContextId = userContext.get().getId();
      
      for (var entityId: entityIds) {
        query.or(
           predicate.withCreator(userContextId).and(predicate.withId(entityId))
        );
      }
      return service.readAll(query).getList().size() >= entityIds.size();
    }
    return false;
  }

}
