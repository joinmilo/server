package app.wooportal.server.base.analytics.googleSearch;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.searchconsole.v1.SearchConsole;
import com.google.api.services.searchconsole.v1.model.ApiDimensionFilter;
import com.google.api.services.searchconsole.v1.model.ApiDimensionFilterGroup;
import com.google.api.services.searchconsole.v1.model.SearchAnalyticsQueryRequest;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;
import app.wooportal.server.core.base.dto.analytics.AnalyticsEntry;

@Service
public class SearchConsoleService {
  
  private final SearchConsoleConfig config;
  
  private final String clicks = "clicks";
  
  private final String impressions = "impressions";
  
  private final String ctr = "ctr";
  
  private final String position = "position";
  
  public SearchConsoleService(
      SearchConsoleConfig searchConfig) {
    this.config = searchConfig;
  }
  
  public SearchAnalyticsDto calculateTotal(
      LocalDate startDate,
      LocalDate endDate) throws IOException {
    var searchService = createSearchService();
    var query = new SearchAnalyticsQueryRequest()
        .setSearchType(null)
        .setStartDate(startDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
        .setEndDate(endDate.format(DateTimeFormatter.ISO_LOCAL_DATE));

    var result = new SearchAnalyticsDto();
    for (var row : searchService
        .searchanalytics()
        .query(config.getHost(), query)
        .execute()
        .getRows()) {
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
      SearchDimension dimension) throws IOException {
    var searchService = createSearchService();
    var query = new SearchAnalyticsQueryRequest()
        .setSearchType(null)
        .setStartDate(startDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
        .setEndDate(endDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
        .setDimensions(Arrays.asList(dimension.toString()));

    var result = new HashMap<String, HashMap<String, Double>>();
    for (var row : searchService
        .searchanalytics()
        .query(config.getHost(), query)
        .execute()
        .getRows()) {
      var entry = result.containsKey(row.getKeys().get(0))
          ? result.get(row.getKeys().get(0))
          : new HashMap<String, Double>();
      entry.put(clicks, 
          entry.containsKey(clicks) ? entry.get(clicks) + row.getClicks() : row.getClicks());
      entry.put(impressions, 
          entry.containsKey(impressions) ? entry.get(impressions) + row.getImpressions() : row.getImpressions());
      
      result.put(row.getKeys().get(0), entry);
    }
    
    return result.entrySet().stream().map(e -> new AnalyticsDto(e.getKey(), e.getValue()))
        .collect(Collectors.toList());
  }
  
  public List<GoogleSearchDto> calculateForFeature(
      LocalDate startDate,
      LocalDate endDate,
      String keyword) throws IOException {
  var searchService = createSearchService();
  var baseQuery = new SearchAnalyticsQueryRequest()
      .setSearchType(null)
      .setStartDate(startDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
      .setEndDate(endDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
      .setDimensions(Arrays.asList("date"));

  var filterGroup = new ApiDimensionFilterGroup();
  var filter = new ApiDimensionFilter();
  filter.setDimension("page");
  filter.setOperator("contains");
  filter.setExpression(keyword);
  filterGroup.setFilters(Arrays.asList(filter));

  var query = baseQuery.setDimensionFilterGroups(Arrays.asList(filterGroup));

  String[] metrics = {"clicks", "impressions", "ctr", "position"};

  List<GoogleSearchDto> googleSearchDtoList = new ArrayList<>();

  for (String metric : metrics) {
      var metricTotal = 0.0;
      var positionCount = 0;
      var ctrCount = 0;
      var metricEntries = new ArrayList<AnalyticsEntry>();

      for (var row : searchService
              .searchanalytics()
              .query(config.getHost(), query)
              .execute()
              .getRows()) {

          var date = row.getKeys().get(0);
          var value = 0.0;

          switch (metric) {
            case "clicks":
              value = row.getClicks();
              break;
            case "impressions":
              value = row.getImpressions();
              break;
            case "ctr":
              value = row.getCtr();
              if (row.getCtr() > 0) {
                ctrCount++;
              }
              break;
            case "position":
              value = row.getPosition();
              if (row.getPosition() > 0) {
                positionCount++;
              }
              break;
          }

          metricTotal += value;

          var analyticsEntry = new AnalyticsEntry();
          analyticsEntry.setName(date);
          analyticsEntry.setValue(value);

          metricEntries.add(analyticsEntry);
      }

      var googleSearchDto = new GoogleSearchDto();
      googleSearchDto.setName(metric);

      googleSearchDto.setValue(
          metricTotal > 0 ? 
          metric.equals("ctr") ?
          metricTotal / ctrCount :
          metric.equals("position") ?  
          metricTotal / positionCount 
          : metricTotal
          : 0);
      googleSearchDto.setEntries(metricEntries);
      
      googleSearchDtoList.add(googleSearchDto);
  }

  return googleSearchDtoList;
}


  public SearchConsole createSearchService() throws IOException {
//  // As of now, this cannot be passed to SearchConsole.Builder
//  var credentials = GoogleCredentials
//    .fromStream(new ClassPathResource(searchConfig.getCredentials())
//        .getInputStream())
//    .createScoped(Collections.singletonList(searchConfig.getScope()));
  
  // Only the deprecated method works
  @SuppressWarnings("deprecation")
  var credentials = GoogleCredential
    .fromStream(new ClassPathResource(config.getCredentials())
        .getInputStream())
    .createScoped(Collections.singletonList(config.getScope()));

  return new SearchConsole.Builder(
      new NetHttpTransport(), 
      new GsonFactory(), 
      credentials).build();
  }
}
