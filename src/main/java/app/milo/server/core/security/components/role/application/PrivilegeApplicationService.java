package app.milo.server.core.security.components.role.application;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.milo.server.core.base.DataService;
import app.milo.server.core.config.GeneralConfiguration;
import app.milo.server.core.messaging.MailService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.core.security.components.role.privilege.RolePrivilegeService;
import app.milo.server.core.security.components.user.UserService;

@Service
public class PrivilegeApplicationService
    extends DataService<PrivilegeApplicationEntity, PrivilegeApplicationPredicateBuilder> {
  
  private final GeneralConfiguration config;
  
  private final MailService mailService;
  
  private final UserService userService;
  
  public PrivilegeApplicationService(DataRepository<PrivilegeApplicationEntity> repo,
      PrivilegeApplicationPredicateBuilder predicate,
      RolePrivilegeService rolePrivilegeService,
      GeneralConfiguration config,
      MailService mailService,
      UserService userService) {
    super(repo, predicate);
    
    this.config = config;
    this.mailService = mailService;
    this.userService = userService;
    
    addService("privilege", rolePrivilegeService);
  }
  
  @Override
  public void postCreate(PrivilegeApplicationEntity entity, PrivilegeApplicationEntity newEntity,
      PrivilegeApplicationEntity saved, JsonNode context) {
    
    var application = repo.findById(saved.getId()).get();

    this.userService.getUsersWithPrivileges("admin").stream()
        .forEach(user -> {
          try {
            mailService.sendEmail("Neue Rollen-Anfrage", "newPrivilegeApplication.ftl",
                Map.of(
                    "userName", user.getFirstName() != null ? " " + user.getFirstName() : "",
                    "portalName", config.getPortalName(),
                    "name", application.getPrivilege().getName(),
                    "link", createApprovePrivilegeLink()),
                user.getEmail());
          } catch (Throwable e) {
            e.printStackTrace();
          }
        });
  }
  
  private String createApprovePrivilegeLink() {
    return config.getHost() + "/admin/settings/access/privilege-applications";
  }    
}
