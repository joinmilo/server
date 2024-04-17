package app.milo.server.core.visit.visitable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import app.milo.server.base.userContext.security.UserContextAuthorizationService;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.config.GeneralConfiguration;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.core.repository.RepositoryService;
import app.milo.server.core.visit.VisitHelper;
import app.milo.server.core.visit.visitor.VisitorEntity;
import app.milo.server.core.visit.visitor.VisitorService;

//TODO: Figure out how to unify DataService approach with the current save and repository approach
@Service
public class VisitableService<V extends VisitableEntity<?>> {
  
  private final Environment env;
  
  private final GeneralConfiguration generalConfig;
  
  private final RepositoryService repoService;
  
  private final VisitorService visitorService;
  
  private final UserContextAuthorizationService authorizationService;
  
  protected String ipAddress;
  protected String remoteAddress;
  protected String userAgent;
  
  public VisitableService(
      UserContextAuthorizationService authorizationService,
      Environment env,
      GeneralConfiguration generalConfig,
      RepositoryService repoService,
      VisitorService visitorService) {
    
    this.authorizationService = authorizationService;
    this.env = env;
    this.generalConfig = generalConfig;
    this.repoService = repoService;
    this.visitorService = visitorService;
  }

  @SuppressWarnings("unchecked")
  public <E extends BaseEntity> void saveEntityVisit(E entity) throws Throwable {
    var visitableClass = VisitHelper.getVisitableType(entity);
    if (visitableClass.isPresent()) {
      var visitableRepo = getRepository(visitableClass.get());
      var visitor = (VisitorEntity) Hibernate.unproxy(createVisitor());
      
      var findMethod = visitableRepo.getClass().getMethod(
          "findByParentIdAndVisitorId", String.class, String.class);

      var visitable = (V) findMethod.invoke(visitableRepo, entity.getId(), visitor.getId());

      if (visitable == null) {
        visitable = (V) visitableClass.get().getDeclaredConstructor().newInstance();
        visitable.setVisitor(visitor);
        visitable.setParent(entity);
        visitable.setId(UUID.randomUUID().toString());
      }
      
      visitable.addVisit();
      repoService.save(visitable);
    }
  }
  
  private VisitorEntity createVisitor() throws ServiceUnavailableException {
    var visitor = new VisitorEntity();
    visitor.setUserAgent(userAgent);
    visitor.setIpAddress(retrieveUserAddress());
    return visitorService.save(visitor);
  }
  
  @SuppressWarnings("unchecked")
  public <T extends VisitableEntity<?>> List<VisitableEntity<?>> getVisitables(
      BaseEntity entity,
      OffsetDateTime startDate,
      OffsetDateTime endDate) throws Throwable {
    var visitableRepo = getRepository(VisitHelper.getVisitableType(entity).get());

    var findMethod = visitableRepo.getClass().getMethod(
        "findByParentIdAndCreatedBetween", String.class, OffsetDateTime.class, OffsetDateTime.class);

    return (List<VisitableEntity<?>>) findMethod
        .invoke(visitableRepo, entity.getId(), startDate, endDate);
  }

  public VisitableRepository<?> getRepository(Class<VisitableEntity<?>> visitableClass) {
    DataRepository<?> repo = repoService.getRepository(visitableClass);
    if (!(repo instanceof VisitableRepository<?>)) {
      throw new RuntimeException(
          "Repository of " + repo.getClass().toString() + " must inherit from " + VisitableRepository.class);
    }
    return (VisitableRepository<?>) repo;
  }

  public boolean isValidVisitor() {
    return isDevMode()
        || !isPrivateIpAddress()
        || !isAdminUser();
  }

  private boolean isDevMode() {
    for(var activeProfile: env.getActiveProfiles()) {
      if (activeProfile.contains("production")) {
        return false;
      }
    }
    return true;
  }

  private boolean isPrivateIpAddress() {
    var ipAddress = retrieveUserAddress();
    return ipAddress.startsWith("192.")
        || ipAddress.startsWith("172.")
        || ipAddress.startsWith("127.")
        || ipAddress.startsWith("10.");
  }
  
  private boolean isAdminUser() {
    return authorizationService.authenticatedUserHasAdminPrivilege();
  }
  
  private String retrieveUserAddress() {
    return ipAddress != null && !ipAddress.isBlank()
        ? ipAddress
        : remoteAddress;
  }

  //TODO: Injecting Request in Async service does not work
  //see: https://stackoverflow.com/questions/53612149/spring-boot-async-get-httpservletrequest-inside-async-method
  public void setRequest(HttpServletRequest request) {
    userAgent = request.getHeader("User-Agent");
    ipAddress = request.getHeader(generalConfig.getClientIpHeader());
    remoteAddress = request.getRemoteAddr();
  }
}
