package app.wooportal.server.base.analytics.googleSearch;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Set;
import org.springframework.stereotype.Service;
import app.wooportal.server.base.analytics.googleSearch.dto.SearchConsoleDimension;
import app.wooportal.server.base.analytics.googleSearch.dto.SearchConsoleQuery;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;
import app.wooportal.server.core.seo.annotations.SlugTarget;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class SearchConsoleService {
  
  private final SearchConsoleApiService apiService;
  
  private final String clicks = "clicks";
  private final String impressions = "impressions";
  private final String ctr = "ctr";
  private final String position = "position";
  
  private final LocalDate majorReleaseDate = LocalDate.of(2023, 10, 1);
  
  public SearchConsoleService(
      SearchConsoleApiService apiService,
      SearchConsoleConfig searchConfig) {
    this.apiService = apiService;
  }

  public Set<AnalyticsDto> getSearchStatistics(
      LocalDate startDate,
      LocalDate endDate,
      SearchConsoleDimension dimension) throws IOException {
    
    if (startDate.isAfter(endDate)) {
      return null;
    }

    return calculate(new SearchConsoleQuery()
        .setStartDate(startDate)
        .setEndDate(endDate)
        .setDimensions(dimension));
  }
  
  /**
   * Calculates the search statistics for entities. The calculation checks if {@code startDate}
   * is before the major release date. This is the release date of the rewritten portal. The difference
   * is that prior to that date the {@code entity.id} field is URL page indicator. After that
   * date, the field {@code slug} is the major indicator. This method takes this circumstance
   * into account, so that past statistics are possible.
   * 
   * @param startDate Date to start calculation
   * @param endDate Date to end calculation
   * @param entity This must be an entity with a field having the annotation {@code @SlugSource} 
   * @return List of all dates with statistics (clicks, impressions, ctr and position) for each day
   * @throws IOException
   */
  public Set<AnalyticsDto> getEntitySearchStatistics(
      LocalDate startDate,
      LocalDate endDate,
      BaseEntity entity) throws IOException {
    
    var slugFields = ReflectionUtils.getFieldsWithAnnotation(entity.getClass(), SlugTarget.class);
    if (startDate.isAfter(endDate)
        || slugFields == null
        || slugFields.isEmpty()) {
      return null;
    }
    
    return startDate.isBefore(majorReleaseDate)
        ? calculateBeforeMajorRelease(startDate, endDate, entity, slugFields.get(0))
        : calculateAfterMajorRelease(startDate, endDate, entity, slugFields.get(0));
  }

  private Set<AnalyticsDto> calculateBeforeMajorRelease(
      LocalDate startDate,
      LocalDate endDate,
      BaseEntity entity,
      Field slugField) throws IOException {
    if (endDate.isBefore(majorReleaseDate)) {
      return calculate(new SearchConsoleQuery()
          .setStartDate(startDate)
          .setEndDate(endDate)
          .setDimensions(SearchConsoleDimension.date)
          .addPageFilter(entity.getId()));
    }
    var resultUntilMajorRelease = calculate(new SearchConsoleQuery()
        .setStartDate(startDate)
        .setEndDate(majorReleaseDate)
        .setDimensions(SearchConsoleDimension.date)
        .addPageFilter(entity.getId()));
    
    var resultAfterMajorRelease = calculateAfterMajorRelease(
        majorReleaseDate.plusDays(1), 
        endDate,
        entity,
        slugField);
    
    return merge(resultUntilMajorRelease, resultAfterMajorRelease);
  }
  
  private Set<AnalyticsDto> merge(
      Set<AnalyticsDto> set1,
      Set<AnalyticsDto> set2) {
    if (set1 != null
        && set2 != null
        && !set2.isEmpty()) 
    for (var element1: set1) {
      for (var element2: set2) {
        if (element1.equals(element2)) {
          element1.addAll(element2.getSeries());
        }
      }
    }
    return set1;
  }

  private Set<AnalyticsDto> calculateAfterMajorRelease(
      LocalDate startDate,
      LocalDate endDate,
      BaseEntity entity,
      Field slugField) throws IOException {
    var slug = ReflectionUtils.get(slugField.getName(), entity);
    
    if (slug.isPresent()) {      
      return calculate(new SearchConsoleQuery()
          .setStartDate(startDate)
          .setEndDate(majorReleaseDate)
          .setDimensions(SearchConsoleDimension.date)
          .addPageFilter(slug.get().toString()));
    }
    return null;
  }

  private Set<AnalyticsDto> calculate(
      SearchConsoleQuery query) throws IOException {
    if (query != null) {
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

      return  Set.of(
          clicksEntry, impressionsEntry, ctrEntry, positionEntry);
    }
    return null;
  }
}
