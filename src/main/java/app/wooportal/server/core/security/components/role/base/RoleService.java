package app.wooportal.server.core.security.components.role.base;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.config.GeneralConfiguration;
import app.wooportal.server.core.messaging.MailService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.components.user.UserService;

@Service
public class RoleService extends DataService<RoleEntity, RolePredicateBuilder> {
  
  private final GeneralConfiguration config;
  private final MailService mailService;
  private final UserService userService;
  
  public RoleService(
      DataRepository<RoleEntity> repo,
      RolePredicateBuilder predicate,
      MailService mailService,
      UserService userService,
      GeneralConfiguration config) {
    super(repo, predicate);
    
    this.config = config;
    this.userService = userService;
    this.mailService = mailService;
  }

  public List<RoleEntity> getByUser(UserEntity user) {
    return repo.findAll(collectionQuery(predicate.withUserId(user.getId()))).getList();
  }
  
  public Boolean addUser(String userId, String roleId) {
    System.out.println("addUsers");
    var role = getById(roleId);
    var user = userService.getById(userId);
    if (user.isPresent() && role.isPresent() && !role.get().getUsers().contains(user.get())) {
      role.get().getUsers().add(user.get());
      repo.save(role.get());
      sendRoleApprovedMail(user.get(), role.get());
      return true;
    }
    return false;
  }
  
  @Override
  public void preSave(RoleEntity entity, RoleEntity newEntity, JsonNode context) {
    if (newEntity.getUsers().size() > entity.getUsers().size()) {
      newEntity.getUsers().stream()
          .filter(user -> entity.getUsers().stream()
              .noneMatch(newUser -> newUser.getId().equals(user.getId())))
          .forEach(user -> sendRoleApprovedMail(userService.getById(user.getId()).get(), newEntity));
    }
  }

  public void sendRoleApprovedMail(UserEntity user, RoleEntity role) {
    try {
      mailService.sendEmail("Neue Rolle", "addedRole.ftl",
          Map.of(
              "userName" , user.getFirstName() != null ? " " + user.getFirstName() : "",
              "portalName", config.getPortalName(),
              "name", role.getName(),
              "link", config.getHost()),
          user.getEmail());
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }
}
