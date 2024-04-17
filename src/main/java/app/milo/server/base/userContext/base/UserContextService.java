package app.milo.server.base.userContext.base;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.milo.server.base.address.base.AddressService;
import app.milo.server.base.userContext.base.media.UserContextMediaService;
import app.milo.server.base.userContext.security.UserContextAuthorizationService;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.core.security.components.user.UserService;
import app.milo.server.core.seo.SlugService;
import app.milo.server.features.organisation.member.OrganisationMemberService;

@Service
public class UserContextService extends DataService<UserContextEntity, UserContextPredicateBuilder> {
  
  private final UserContextAuthorizationService authService;
  
  private final SlugService slugService;

  public UserContextService(
      DataRepository<UserContextEntity> repo,
      UserContextPredicateBuilder predicate,
      AddressService addressService,
      UserContextAuthorizationService authService,
      OrganisationMemberService memberService,
      SlugService slugService,
      UserService userService,
      UserContextMediaService userContextMedia) {
    super(repo, predicate);
    
    addService("user", userService);
    addService("address", addressService);
    addService("members", memberService);
    addService("uploads", userContextMedia);
    
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
    var currentUser = authService.getAuthenticatedUserContext();
    if (currentUser.isPresent()) {
      return repo.findOne(singleQuery(predicate.withId(currentUser.get().getId())));
    }
    return Optional.empty();
  }

  public Optional<UserContextEntity> getByEmail(String username) {
    return repo.findOne(singleQuery(predicate.withEmail(username))
        .addGraph(graph("user.roles.privileges")));
  }
  
	public Optional<UserContextEntity> saveMe(UserContextEntity entity) {
		var currentUser = authService.getAuthenticatedUserContext();
		if (currentUser.isPresent()) {
			entity.setId(currentUser.get().getId());
			return Optional.of(saveWithContext(entity));
		}
		return currentUser;
	}
}
