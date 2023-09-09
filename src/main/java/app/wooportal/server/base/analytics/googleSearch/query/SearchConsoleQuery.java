package app.wooportal.server.base.analytics.googleSearch.query;

import java.time.LocalDate;
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
  
  public SearchConsoleQuery setStartDate(LocalDate startDate) {
    if (startDate != null) {
      query.setStartDate(startDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
   return this;
  }
  
  public SearchConsoleQuery setEndDate(LocalDate endDate) {
    if (endDate != null) {
      query.setEndDate(endDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
   return this;
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
