package app.milo.server.features.organisation.base;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.milo.server.base.address.base.AddressService;
import app.milo.server.base.contact.ContactService;
import app.milo.server.base.userContext.security.UserContextAuthorizationService;
import app.milo.server.core.base.DataService;
import app.milo.server.core.config.GeneralConfiguration;
import app.milo.server.core.messaging.MailService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.core.security.components.user.UserService;
import app.milo.server.features.organisation.media.OrganisationMediaService;
import app.milo.server.features.organisation.member.OrganisationMemberEntity;
import app.milo.server.features.organisation.member.OrganisationMemberService;

@Service
public class OrganisationService extends DataService<OrganisationEntity, OrganisationPredicateBuilder> {
  
  private final UserContextAuthorizationService authService;
  private final GeneralConfiguration config;
  private final MailService mailService;
  private final UserService userService;

  public OrganisationService(
      DataRepository<OrganisationEntity> repo,
      OrganisationPredicateBuilder predicate,
      UserContextAuthorizationService authService,
      AddressService addressService,
      ContactService contactService,
      GeneralConfiguration config,
      OrganisationMemberService memberService,
      MailService mailService,
      OrganisationMediaService uploadService,
      UserService userService) {
    super(repo, predicate);
    
    this.authService = authService;
    this.config = config;
    this.mailService = mailService;
    this.userService = userService;
    
    addService("address", addressService);
    addService("contact", contactService);
    addService("members", memberService);
    addService("uploads", uploadService);
  }
  
  @Override
  public void preCreate(OrganisationEntity entity, OrganisationEntity newEntity, JsonNode context) {
    newEntity.setSponsored(false);
    addContext("sponsored", context);
    
    newEntity.setApproved(false);
    addContext("approved", context);

    if (authService.authenticatedUserHasPrivilege("organisations_admin")) {
      newEntity.setApproved(true);
      addContext("approved", context);
    }
  }  
  
  @Override
  public void postCreate(OrganisationEntity entity, OrganisationEntity newEntity,
      OrganisationEntity saved, JsonNode context) {
    var currentUser = authService.getAuthenticatedUserContext();

    if (currentUser.isPresent()) {
      var firstMember = new OrganisationMemberEntity();
      firstMember.setApproved(true);
      firstMember.setUserContext(currentUser.get());

      firstMember.setIsPublic(true);
      firstMember.setUserContext(currentUser.get());

      firstMember.setOrganisation(saved);

      getService(OrganisationMemberService.class).persist(firstMember);
    }

    this.userService.getUsersWithPrivileges("organisations_admin", "admin").stream().forEach(user -> {
      try {
        mailService.sendEmail("Neue Organisation", 
            newEntity.getApproved() ? "newApprovedOrga.ftl" : "newOrga.ftl",
            Map.of(
                "userName" , user.getFirstName() != null ? " " + user.getFirstName() : "",
                "portalName", config.getPortalName(),
                "name", saved.getName(),
                "link", createApproveOrganisationLink(saved.getApproved())),
            user.getEmail());
      } catch (Throwable e) {
        e.printStackTrace();
      }
    });
  }
  
  public Boolean sponsor(String rganisationId) {
    var rganisation = getById(rganisationId);
    
    if (rganisation.isPresent()) {
      rganisation.get().setSponsored(true);
      repo.save(rganisation.get());
      
      unsponsorOther(rganisationId);
      //TODO: Send notifications
      return true;
    }
    return false;
  }

  private void unsponsorOther(String organisationId) {
		var other = readAll(collectionQuery(predicate.withSponsoredTrue().and(predicate.withoutId(organisationId))));
    if (other != null && !other.isEmpty()) {
      other.getList().stream().forEach(rganisation -> {
        rganisation.setSponsored(false);
        repo.save(rganisation);
      });
    }
  }
  
  public Boolean changeApproval(String organisationId) {
    var organisation = getById(organisationId);
    
    if (organisation.isPresent()) {
      organisation.get().setApproved(!organisation.get().getApproved());
      repo.save(organisation.get());
     
      if (organisation.get().getApproved()) {        
        getService(OrganisationMemberService.class).addRoleForMembers(organisation.get().getId());
      }
      return true;
    }
    return false;
  }
  
  private String createApproveOrganisationLink(Boolean approved) {
    var linkPiece = approved ? "/admin/organisations" : "/admin/organisations/approval";
    return config.getHost() + linkPiece;
  }
}
