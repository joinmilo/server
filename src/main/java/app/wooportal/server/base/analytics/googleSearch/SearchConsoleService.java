package app.wooportal.server.base.analytics.googleSearch;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import app.wooportal.server.base.analytics.googleSearch.dto.SearchConsoleDetailsDto;
import app.wooportal.server.base.analytics.googleSearch.dto.SearchConsoleSummaryDto;
import app.wooportal.server.base.analytics.googleSearch.query.SearchConsoleDimension;
import app.wooportal.server.base.analytics.googleSearch.query.SearchConsoleOperator;
import app.wooportal.server.base.analytics.googleSearch.query.SearchConsoleQuery;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;
import app.wooportal.server.core.base.dto.analytics.AnalyticsEntry;

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
  
  public SearchConsoleSummaryDto calculateTotal(
      LocalDate startDate,
      LocalDate endDate) throws IOException {
    var query = new SearchConsoleQuery()
        .setStartDate(startDate)
        .setEndDate(endDate);

    var result = new SearchConsoleSummaryDto();
    for (var row : apiService.execute(query)) {
      result.addClicks(row.getClicks().intValue());
      result.addImpressions(row.getImpressions().intValue());
      result.addPosition(row.getPosition());
      result.addCtr(row.getCtr());
    }
    
    return result;
  }

  public List<AnalyticsDto> calculateForDimension(
      LocalDate startDate,
      LocalDate endDate,
      SearchConsoleDimension dimension) throws IOException {
    var query = new SearchConsoleQuery()
      .setStartDate(startDate)
      .setEndDate(endDate)
      .setDimensions(dimension);

    var result = new HashMap<String, HashMap<String, Double>>();
    for (var row : apiService.execute(query)) {
      var entry = result.containsKey(row.getKeys().get(0))
          ? result.get(row.getKeys().get(0))
          : new HashMap<String, Double>();

      entry.put(clicks, 
          entry.containsKey(clicks)
            ? entry.get(clicks) + row.getClicks()
            : row.getClicks());

      entry.put(impressions,
          entry.containsKey(impressions)
            ? entry.get(impressions) + row.getImpressions()
            : row.getImpressions());
      
      //TODO: Why not all?
      
      result.put(row.getKeys().get(0), entry);
    }
    
    return result.entrySet().stream()
        .map(e -> new AnalyticsDto(e.getKey(), e.getValue()))
        .collect(Collectors.toList());
  }
  
  public List<SearchConsoleDetailsDto> calculateForPage(
      LocalDate startDate,
      LocalDate endDate,
      String page) throws IOException {
    var query = new SearchConsoleQuery()
        .setStartDate(startDate)
        .setEndDate(endDate)
        .setDimensions(SearchConsoleDimension.page)
        .addPageFilter(page);
    
    var totalClicks = 0.0;
    var totalImpressions = 0.0;
    var totalPosition = 0.0;
    var totalCtr = 0.0;

    var result = new HashMap<String, HashMap<String, Double>>();
    
    var rows = apiService.execute(query);
    for (var row : rows) {

      var date = row.getKeys().get(0);
      
      var clicksEntry = result.containsKey(clicks)
          ? result.get(clicks)
          : new HashMap<String, Double>();
      clicksEntry.put(date, row.getClicks());
      totalClicks += row.getClicks();

      var impressionsEntry = result.containsKey(impressions)
          ? result.get(impressions)
          : new HashMap<String, Double>();
      impressionsEntry.put(impressions, row.getImpressions());
      totalImpressions += row.getImpressions();
      
      var ctrEntry = result.containsKey(ctr)
          ? result.get(ctr)
          : new HashMap<String, Double>();
      ctrEntry.put(ctr, row.getCtr());
      totalCtr += row.getCtr();
      
      var positionEntry = result.containsKey(position)
          ? result.get(position)
          : new HashMap<String, Double>();
      positionEntry.put(position, row.getPosition());
      totalPosition += row.getPosition();
    }
    
    
    return null;
  }
  
  public List<SearchConsoleDetailsDto> calculateForPage1(
      LocalDate startDate,
      LocalDate endDate,
      String keyword) throws IOException {
    var query = new SearchConsoleQuery()
        .setStartDate(startDate)
        .setEndDate(endDate)
        .setDimensions(SearchConsoleDimension.date)
        .addFilter(SearchConsoleDimension.page,
            SearchConsoleOperator.contains,
            keyword);
  
    String[] metrics = { clicks, impressions, ctr, position };
  
    var googleSearchDtoList = new ArrayList<SearchConsoleDetailsDto>();
  
    for (String metric : metrics) {
        var metricTotal = 0.0;
        var positionCount = 0;
        var ctrCount = 0;
        var metricEntries = new ArrayList<AnalyticsEntry>();
  
        for (var row : apiService.execute(query)) {
  
            var date = row.getKeys().get(0);
            var value = 0.0;
  
            switch (metric) {
              case clicks:
                value = row.getClicks();
                break;
              case impressions:
                value = row.getImpressions();
                break;
              case ctr:
                value = row.getCtr();
                if (row.getCtr() > 0) {
                  ctrCount++;
                }
                break;
              case position:
                value = row.getPosition();
                if (row.getPosition() > 0) {
                  positionCount++;
                }
                break;
            }
  
            metricTotal += value;
  
            metricEntries.add(new AnalyticsEntry()
                .setName(date)
                .setValue(value));
        }
  
        var googleSearchDto = new SearchConsoleDetailsDto();
        googleSearchDto.setName(metric);
  
        googleSearchDto.setTotal(
            metricTotal > 0
              ? metric.equals("ctr")
                  ? metricTotal / ctrCount
                  : metric.equals("position")
                    ?  metricTotal / positionCount 
                    : metricTotal
              : 0);
        googleSearchDto.setSeries(metricEntries);
        
        googleSearchDtoList.add(googleSearchDto);
    }
  
    return googleSearchDtoList;
  }

}
