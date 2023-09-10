package app.wooportal.server.base.analytics.googleSearch;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.base.analytics.googleSearch.dto.SearchConsoleDimension;
import app.wooportal.server.base.analytics.googleSearch.dto.SearchConsoleQuery;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;

@Service
public class SearchConsoleService {
  
  private final SearchConsoleApiService apiService;
  
  private final String clicks = "clicks";
  
  private final String impressions = "impressions";
  
  private final String ctr = "ctr";
  
  private final String position = "position";
  
  public SearchConsoleService(
      SearchConsoleApiService apiService,
      SearchConsoleConfig searchConfig) {
    this.apiService = apiService;
  }

  public List<AnalyticsDto> getSearchStatistics(
      LocalDate startDate,
      LocalDate endDate,
      SearchConsoleDimension dimension) throws IOException {
    var query = new SearchConsoleQuery()
      .setStartDate(startDate)
      .setEndDate(endDate)
      .setDimensions(dimension);

    return calculate(query);
  }
  
  public List<AnalyticsDto> getSearchStatisticsForPage(
      LocalDate startDate,
      LocalDate endDate,
      String page) throws IOException {

    var query = new SearchConsoleQuery()
        .setStartDate(startDate)
        .setEndDate(endDate)
        .setDimensions(SearchConsoleDimension.page)
        .addPageFilter(page);
   
    return calculate(query);
  }

  private List<AnalyticsDto> calculate(
      SearchConsoleQuery query) throws IOException {
    var clicksEntry = new AnalyticsDto()
        .setName(clicks);
    var impressionsEntry = new AnalyticsDto()
        .setName(impressions);
    var ctrEntry = new AnalyticsDto()
        .setName(ctr);
    var positionEntry = new AnalyticsDto()
        .setName(position);

    var rows = apiService.execute(query);
    for (var row : rows) {
      var key = row.getKeys().get(0);
      
      clicksEntry.add(key, row.getClicks());
      impressionsEntry.add(key, row.getImpressions());
      ctrEntry.add(key, row.getCtr());
      positionEntry.add(key, row.getPosition());
    }
    
    return List.of(
        clicksEntry, impressionsEntry, ctrEntry, positionEntry);
  }
}
