package app.wooportal.server.base.userContext.base;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.core.security.services.AuthenticationService;
import app.wooportal.server.core.seo.SlugService;

@Service
public class UserContextService extends DataService<UserContextEntity, UserContextPredicateBuilder> {
  
  private final AuthenticationService authService;
  
  private final SlugService slugService;

  public UserContextService(
      DataRepository<UserContextEntity> repo,
      UserContextPredicateBuilder predicate,
      AuthenticationService authService,
      SlugService slugService,
      UserService userService) {
    super(repo, predicate);
    
    addService("user", userService);
    
    this.authService = authService;
    this.slugService = slugService;
  }
  
  @Override
  protected void preCreate(UserContextEntity entity, UserContextEntity newEntity, JsonNode context) {
    if (entity.getSlug() == null) {
      entity.setSlug(slugService.slugify(newEntity.getUser().getEmail()));
    }
    
  }
  
  public Optional<UserContextEntity> me() {
    var currentUser = authService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      return repo.findOne(singleQuery(predicate.withUser(currentUser.get().getId())));
    }
    return null;
  }
}
