package app.wooportal.server.core.visit.visitor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VisitorScheduler {
  
  private final VisitorService visitorService;
  
  public VisitorScheduler(VisitorService visitorService) {
    this.visitorService = visitorService;
  } 
  
  @Scheduled(cron = "0 0 3 * * ?")
  public void deleteIpAddresses() {
    visitorService.emptyIdAddresses();
  }

}
