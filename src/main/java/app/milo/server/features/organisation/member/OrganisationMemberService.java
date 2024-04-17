package app.milo.server.features.organisation.member;

import java.util.Map;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.milo.server.core.base.DataService;
import app.milo.server.core.config.GeneralConfiguration;
import app.milo.server.core.messaging.MailService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.core.security.components.user.UserService;
import app.milo.server.core.security.services.AuthenticationService;
import app.milo.server.features.organisation.base.OrganisationService;
import app.milo.server.features.organisation.configuration.OrganisationConfigurationService;

@Service
public class OrganisationMemberService
    extends DataService<OrganisationMemberEntity, OrganisationMemberPredicateBuilder> {

  private final OrganisationConfigurationService configurationService;

  private final OrganisationService organisationService;

  private final UserService userService;

  private final MailService mailService;

  private final GeneralConfiguration config;
  
  private final AuthenticationService authService;

  public OrganisationMemberService(DataRepository<OrganisationMemberEntity> repo,
      OrganisationMemberPredicateBuilder predicate,
      OrganisationConfigurationService configurationService,
      @Lazy OrganisationService organisationService, UserService userService,
      GeneralConfiguration config, MailService mailService, AuthenticationService authService) {
    super(repo, predicate);

    this.configurationService = configurationService;
    this.organisationService = organisationService;
    this.userService = userService;
    this.config = config;
    this.mailService = mailService;
    this.authService = authService;  }

  @Override
  public void preCreate(OrganisationMemberEntity entity, OrganisationMemberEntity newEntity,
      JsonNode context) {
    newEntity.setApproved(false);
    addContext("approved", context);

    newEntity.setApproved(false);
    addContext("isPublic", context);
  }


  @Override
  public void postCreate(OrganisationMemberEntity entity, OrganisationMemberEntity newEntity,
      OrganisationMemberEntity saved, JsonNode context) {

    var currentUser = authService.getAuthenticatedUser();
    var organisation = organisationService.getById(newEntity.getOrganisation().getId()).get();

    if (currentUser.isPresent()) {
      repo.findAll(
          collectionQuery(predicate.withOrganisationId(newEntity.getOrganisation().getId()))
              .and(predicate.withApprovedTrue()))
          .getList().forEach(member -> {
            try {
              var user = member.getUserContext().getUser();
              mailService.sendEmail("Neue Mitgliedsanfrage", "applicationOrga.ftl",
                  Map.of(
                      "userName", user.getFirstName() != null ? " " + user.getFirstName() : "",
                      "newMemberEmail", currentUser.get().getEmail(),
                      "orgaName", organisation.getName(),
                      "link", createOrganisationAddMemberLink(organisation.getSlug()),
                      "portalName", config.getPortalName()),
                    user.getEmail());
            } catch (Throwable e) {
              e.printStackTrace();
            }
          });
    }
  }

  @Override
  public void preUpdate(OrganisationMemberEntity entity, OrganisationMemberEntity newEntity,
      JsonNode context) {
    addMemberRoleToUser(entity);
  }

  public void addRoleForMembers(String organisationId) {
    var members = repo.findAll(collectionQuery(predicate.withOrganisationId(organisationId)));

    if (!members.isEmpty()) {
      saveAll(members.getList());
    }

  }

  private void addMemberRoleToUser(OrganisationMemberEntity entity) {
    var config = configurationService.readAll(
        configurationService.collectionQuery(false).addGraph(configurationService.graph("role")))
        .get(0);

    var member = repo.findOne(singleQuery(predicate.withId(entity.getId()))
        .addGraph(graph("userContext.user.roles", "organisation")));

    if (member.isPresent() && config != null) {

      if (organisationService.exists(organisationService.getPredicate()
          .withApprovedOrga(member.get().getOrganisation().getId()))) {
        var user = member.get().getUserContext().getUser();

        if (user.getRoles().stream()
            .noneMatch(r -> r.getId().equals(config.getMemberRole().getId()))) {
          if (!user.getRoles().contains(config.getMemberRole())) {
            user.getRoles().add(config.getMemberRole());
            userService.save(user);
          }
        }
      }
    }
  }
  
  private String createOrganisationAddMemberLink(String slug) {
    return config.getHost() + "/admin/organisations/" + slug + "/applications";
  }
}
