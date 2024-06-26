package app.milo.server.core.visit.visitable;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.base.dto.analytics.AnalyticsDto;
import app.milo.server.core.base.dto.analytics.IntervalFilter;
import app.milo.server.core.utils.DateUtils;

@Service
public class VisitableAnalyticsService<E extends BaseEntity, V extends VisitableEntity<E>> {
  
  private final VisitableService<V> visitableService;
  
  private final String visits = "visits";
  
  private final String visitors = "visitors";
  
  public VisitableAnalyticsService(
      VisitableService<V> visitableService) {
    this.visitableService = visitableService;
  }
  
  public CompletableFuture<Set<AnalyticsDto>> getEntityVisitableStatistics(
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval,
      E entity) throws Throwable {
    
    var result = visitableService.getVisitables(
        entity, startDate, endDate);
    
    var visits = new AnalyticsDto()
        .setName(this.visits)
        .setSeries(DateUtils.generatePeriodContainer(startDate, endDate, interval, 0.0));
    
    var visitors = new AnalyticsDto()
        .setName(this.visitors)
        .setSeries(DateUtils.generatePeriodContainer(startDate, endDate, interval, 0.0));
    
    for (var visitor: result) {
      var key = DateUtils.format(visitor.getCreated(), interval);
      
      visits.add(key, visitor.getVisits());
      visitors.add(key, 1);
    }
    
    return CompletableFuture.completedFuture(
        Set.of(visitors, visits)
    );
  }

}
