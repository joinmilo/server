package app.wooportal.server.base.analytics.googleSearch;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;
import app.wooportal.server.base.analytics.googleSearch.dto.SearchConsoleSummaryDto;
import app.wooportal.server.base.analytics.googleSearch.query.SearchConsoleDimension;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;
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
  
  @GraphQLQuery(name = "getSearchConsoleOverview")
  public SearchConsoleSummaryDto searchConsoleOverview(
      LocalDate startDate,
      LocalDate endDate) throws IOException {
    return service.calculateTotal(startDate, endDate);
  }
  
  @GraphQLQuery(name = "getSearchConsoleDetails")
  public List<AnalyticsDto> searchConsoleDetails(
      LocalDate startDate,
      LocalDate endDate,
      SearchConsoleDimension dimension) throws IOException {
    return service.calculateForDimension(startDate, endDate, dimension);
  }
}
