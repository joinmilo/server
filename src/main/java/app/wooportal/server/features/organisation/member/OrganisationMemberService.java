package app.wooportal.server.features.organisation.member;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.features.organisation.base.OrganisationService;
import app.wooportal.server.features.organisation.configuration.OrganisationConfigurationService;

@Service
public class OrganisationMemberService extends DataService<OrganisationMemberEntity, OrganisationMemberPredicateBuilder> {
  
  private final OrganisationConfigurationService configurationService;
  
  private final OrganisationService organisationService;
  
  private final UserService userService;

  public OrganisationMemberService(
      DataRepository<OrganisationMemberEntity> repo,
      OrganisationMemberPredicateBuilder predicate,
      OrganisationConfigurationService configurationService,
      @Lazy OrganisationService organisationService,
      UserService userService) {
    super(repo, predicate);
    
    this.configurationService = configurationService;
    this.organisationService = organisationService;
    this.userService = userService;
  }
  
  @Override
  public void preCreate(
      OrganisationMemberEntity entity,
      OrganisationMemberEntity newEntity,
      JsonNode context) {    
    newEntity.setApproved(false);
    addContext("approved", context);
    
    newEntity.setApproved(false);
    addContext("isPublic", context);
  }
  
  @Override
  public void preUpdate(
      OrganisationMemberEntity entity,
      OrganisationMemberEntity newEntity,
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
        configurationService.collectionQuery(false).addGraph(configurationService.graph("role"))
    ).get(0);
    
    var member = repo.findOne(
        singleQuery(predicate.withId(entity.getId())).addGraph(graph("userContext.user.roles", "organisation"))
    );
        
    if (member.isPresent() && config != null) {
      
      if (organisationService.exists(
          organisationService.getPredicate().withApprovedOrga(member.get().getOrganisation().getId()))) {        
        var user = member.get().getUserContext().getUser();
        
        if (user.getRoles().stream().noneMatch(r -> r.getId().equals(config.getMemberRole().getId()))) {        
          if (!user.getRoles().contains(config.getMemberRole())) {
            user.getRoles().add(config.getMemberRole());
            userService.save(user);
          }
        }
      }
    }
  }
  
  
}
