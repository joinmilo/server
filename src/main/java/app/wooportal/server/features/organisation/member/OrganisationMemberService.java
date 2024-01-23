package app.wooportal.server.features.organisation.member;

import java.util.Map;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.config.GeneralConfiguration;
import app.wooportal.server.core.messaging.MailService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.core.security.services.AuthenticationService;
import app.wooportal.server.features.organisation.base.OrganisationService;
import app.wooportal.server.features.organisation.configuration.OrganisationConfigurationService;

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
              mailService.sendEmail("Neue Mitgliedsanfrage", "applicationOrga.ftl",
                  Map.of(
                      "userName", member.getUserContext().getUser().getFirstName(),
                      "newMemberEmail", currentUser.get().getEmail(),
                      "orgaName", organisation.getName(),
                      "link", createOrganisationAddMemberLink(organisation.getSlug()),
                      "portalName", config.getPortalName()),
                  member.getUserContext().getUser().getEmail());
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
