package app.milo.server.base.analytics.search;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Set;

import org.springframework.stereotype.Component;
import app.milo.server.base.analytics.search.dto.SearchConsoleDimension;
import app.milo.server.core.base.dto.analytics.AnalyticsDto;
import app.milo.server.core.base.dto.analytics.IntervalFilter;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class SearchConsoleApi {
  
  private final SearchConsoleService service;
  
  public SearchConsoleApi(
      SearchConsoleService service) {
    this.service = service;
  }
  
  @GraphQLQuery(name = "getSearchStatistics")
  public Set<AnalyticsDto> searchConsoleDetails(
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter filter,
      SearchConsoleDimension dimension) throws IOException {
    return service.getSearchStatistics(startDate, endDate, filter, dimension);
  }
}
