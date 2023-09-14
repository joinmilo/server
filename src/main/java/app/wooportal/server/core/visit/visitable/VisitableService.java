package app.wooportal.server.core.visit.visitable;

import java.util.List;
import java.util.UUID;
import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.config.GeneralConfiguration;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.repository.RepositoryService;
import app.wooportal.server.core.visit.VisitHelper;
import app.wooportal.server.core.visit.visitor.VisitorEntity;
import app.wooportal.server.core.visit.visitor.VisitorService;

@Service
public class VisitableService<V extends VisitableEntity<?>> {
  
  @Autowired
  private Environment env;
  
  @Autowired
  private GeneralConfiguration generalConfig;
  
  protected String ipAddress;
  protected String remoteAddress;
  protected String userAgent;
  
  @Autowired
  private RepositoryService repoService;
  
  @Autowired
  private VisitorService visitorService;

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
  
  public List<VisitableEntity<?>> getVisitablesForEntity(BaseEntity entity) 
      throws Throwable {
    var result = getVisitables(entity);
    if (result != null && !result.isEmpty()) {
      return result;
    }
    throw new NotFoundException("no visitors so far");
  }
  
  @SuppressWarnings("unchecked")
  private List<VisitableEntity<?>> getVisitables(BaseEntity entity) throws Throwable {
    var visitableRepo = getRepository(VisitHelper.getVisitableType(entity).get());
    
    var findMethod = visitableRepo.getClass().getMethod(
        "findByParentId", String.class);

    return (List<VisitableEntity<?>>) findMethod.invoke(visitableRepo, entity.getId());
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
    return isDevMode() || !isPrivateIpAddress();
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
  
  private String retrieveUserAddress() {
    return ipAddress != null && !ipAddress.isBlank()
        ? ipAddress
        : remoteAddress;
  }

  public void setRequest(HttpServletRequest request) {
    userAgent = request.getHeader("User-Agent");
    ipAddress = request.getHeader(generalConfig.getClientIpHeader());
    remoteAddress = request.getRemoteAddr();
  }
}
