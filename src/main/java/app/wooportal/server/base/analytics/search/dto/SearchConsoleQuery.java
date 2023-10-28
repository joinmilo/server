package app.wooportal.server.base.analytics.search.dto;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import com.google.api.services.searchconsole.v1.model.ApiDimensionFilter;
import com.google.api.services.searchconsole.v1.model.ApiDimensionFilterGroup;
import com.google.api.services.searchconsole.v1.model.SearchAnalyticsQueryRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SearchConsoleQuery {
  
  public static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE
      .withZone(ZoneOffset.UTC);
  
  /**
   *  @see https://developers.google.com/webmaster-tools/v1/searchanalytics/query
   */
  private SearchAnalyticsQueryRequest query = new SearchAnalyticsQueryRequest()
      .setDataState(SearchConsoleDataState.ALL.toString());

  public SearchConsoleQuery setDimensions(SearchConsoleDimension... dimensions) {
    if (dimensions != null && dimensions.length > 0) {
      query.setDimensions(Arrays.asList(dimensions)
          .stream().map(dimension -> dimension.toString())
          .toList());
    }
    return this;
  }
  
  public SearchConsoleQuery setStartDate(OffsetDateTime startDate) {
    if (startDate != null) {
      query.setStartDate(startDate.format(formatter));
    }
   return this;
  }
  
  public OffsetDateTime getStartDate() {
    return OffsetDateTime.parse(query.getStartDate(), formatter);
  }
  
  public SearchConsoleQuery setEndDate(OffsetDateTime endDate) {
    if (endDate != null) {
      query.setEndDate(endDate.format(formatter));
    }
   return this;
  }
  
  public OffsetDateTime getEndDate() {
    return OffsetDateTime.parse(query.getEndDate(), formatter);
  }
  
  public SearchConsoleQuery addPageFilter(
      String page) {
    if (page != null) {
      addFilter(
          SearchConsoleDimension.page,
          SearchConsoleOperator.contains,
          page);
    }
    return this;
  }
  
  public SearchConsoleQuery addFilter(
      SearchConsoleDimension dimension,
      SearchConsoleOperator operator,
      String urlPart) {
    if (dimension != null && operator != null && urlPart != null) {      
      query.setDimensionFilterGroups(Arrays.asList(new ApiDimensionFilterGroup()
          .setFilters(Arrays.asList(new ApiDimensionFilter()
              .setDimension(dimension.toString())
              .setOperator(operator.toString())
              .setExpression(urlPart)))));
    }
    return this;
  }

}
