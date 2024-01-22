package app.wooportal.server.features.organisation.base;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.base.address.base.AddressService;
import app.wooportal.server.base.contact.ContactService;
import app.wooportal.server.base.userContext.security.UserContextAuthorizationService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.config.GeneralConfiguration;
import app.wooportal.server.core.messaging.MailService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.features.organisation.media.OrganisationMediaService;
import app.wooportal.server.features.organisation.member.OrganisationMemberEntity;
import app.wooportal.server.features.organisation.member.OrganisationMemberService;

@Service
public class OrganisationService extends DataService<OrganisationEntity, OrganisationPredicateBuilder> {
  
  private final UserContextAuthorizationService authService;
  private final MailService mailService;
  private final GeneralConfiguration config;
  private final UserService userService;

  public OrganisationService(
      DataRepository<OrganisationEntity> repo,
      OrganisationPredicateBuilder predicate,
      UserContextAuthorizationService authService,
      AddressService addressService,
      ContactService contactService,
      OrganisationMediaService uploadService,
      OrganisationMemberService memberService,
      MailService mailService,
      GeneralConfiguration config,
      UserService userService) {
    super(repo, predicate);
    
    this.authService = authService;
    this.mailService = mailService;
    this.config = config;
    this.userService = userService;
    
    addService("members", memberService);
    addService("address", addressService);
    addService("contact", contactService);
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

    this.userService.getOrganisationAdmins().getList().stream().forEach(user -> {
      try {
        mailService.sendEmail("Neue Organisation", "newOrga.ftl",
            Map.of("portalName", config.getPortalName(), "name", newEntity.getName()),
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
}
