package app.wooportal.server.base.analytics.search;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import app.wooportal.server.base.analytics.search.dto.SearchConsoleDimension;
import app.wooportal.server.base.analytics.search.dto.SearchConsoleQuery;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;
import app.wooportal.server.core.base.dto.analytics.AnalyticsOperation;
import app.wooportal.server.core.base.dto.analytics.IntervalFilter;
import app.wooportal.server.core.seo.annotations.SlugTarget;
import app.wooportal.server.core.utils.DateUtils;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class SearchConsoleService {
  
  private final SearchConsoleApiService apiService;
  
  private final String clicks = "clicks";
  private final String impressions = "impressions";
  private final String ctr = "ctr";
  private final String position = "position";
  
  private final OffsetDateTime majorReleaseDate = OffsetDateTime
      .of(2023, 10, 1, 0, 0, 0, 0, ZoneOffset.UTC);
  
  public SearchConsoleService(
      SearchConsoleApiService apiService,
      SearchConsoleConfig searchConfig) {
    this.apiService = apiService;
  }

  public Set<AnalyticsDto> getSearchStatistics(
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval,
      SearchConsoleDimension dimension) throws IOException {
    
    if (startDate.isAfter(endDate)) {
      return null;
    }

    return calculate(
        new SearchConsoleQuery()
          .setStartDate(startDate)
          .setEndDate(endDate)
          .setDimensions(dimension),
        interval);
  }
  
  /**
   * Calculates the search statistics for entities. The calculation checks if {@code startDate}
   * is before the major release date. This is the release date of the rewritten portal. The difference
   * is that prior to that date the {@code entity.id} field is URL page indicator. After that
   * date, the field {@code slug} is the major indicator. This method takes this circumstance
   * into account, so that past statistics are possible.
   * 
   * @param startDate Date to start calculation, must be before endDate
   * @param endDate Date to end calculation, must be before endDate
   * @param entity This must be an entity with a field having the annotation {@code @SlugSource}
   * @param interval Defines the timer containers in which the result should be aggregated
   * @return List of all dates with statistics (clicks, impressions, ctr and position) for each day
   * @throws IOException
   */
  public CompletableFuture<Set<AnalyticsDto>> getEntitySearchStatistics(
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval,
      BaseEntity entity) throws IOException {
    
    var slugFields = ReflectionUtils.getFieldsWithAnnotation(entity.getClass(), SlugTarget.class);
    if (startDate == null
        || endDate == null
        || startDate.isAfter(endDate)
        || slugFields == null
        || slugFields.isEmpty()) {
      return null;
    }
    
    return CompletableFuture.completedFuture(
        startDate.isBefore(majorReleaseDate)
          ? calculateBeforeMajorRelease(startDate, endDate, interval, entity, slugFields.get(0))
          : calculateAfterMajorRelease(startDate, endDate, interval, entity, slugFields.get(0))
        );
  }

  private Set<AnalyticsDto> calculateBeforeMajorRelease(
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval,
      BaseEntity entity,
      Field slugField) throws IOException {
    if (endDate.isBefore(majorReleaseDate)) {
      return calculate(
          new SearchConsoleQuery()
            .setStartDate(startDate)
            .setEndDate(endDate)
            .setDimensions(SearchConsoleDimension.date)
            .addPageFilter(entity.getId()),
          interval);
    }
    var resultUntilMajorRelease = calculate(
        new SearchConsoleQuery()
          .setStartDate(startDate)
          .setEndDate(majorReleaseDate)
          .setDimensions(SearchConsoleDimension.date)
          .addPageFilter(entity.getId()),
        interval);
    
    var resultAfterMajorRelease = calculateAfterMajorRelease(
        majorReleaseDate.plusDays(1), 
        endDate,
        interval,
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
      OffsetDateTime startDate,
      OffsetDateTime endDate,
      IntervalFilter interval,
      BaseEntity entity,
      Field slugField) throws IOException {
    var slug = ReflectionUtils.get(slugField.getName(), entity);
    
    if (slug.isPresent()) {      
      return calculate(
          new SearchConsoleQuery()
            .setStartDate(startDate)
            .setEndDate(majorReleaseDate)
            .setDimensions(SearchConsoleDimension.date)
            .addPageFilter(slug.get().toString()),
          interval);
    }
    return null;
  }

  private Set<AnalyticsDto> calculate(
      SearchConsoleQuery query,
      IntervalFilter interval) throws IOException {
    if (query != null) {
      var clicksEntry = new AnalyticsDto()
          .setName(clicks);
      var impressionsEntry = new AnalyticsDto()
          .setName(impressions);
      var ctrEntry = new AnalyticsDto()
          .setName(ctr)
          .setEntryOperation(AnalyticsOperation.AVG)
          .setAllowNull(false);
      
      var positionEntry = new AnalyticsDto()
          .setName(position)
          .setEntryOperation(AnalyticsOperation.AVG)
          .setAllowNull(false);
      
      var rows = apiService.execute(query);
      for (var row : rows) {
        var key = interval != null
            ? DateUtils.formatLocalDate(row.getKeys().get(0), SearchConsoleQuery.formatter, interval)
            : row.getKeys().get(0);
        
        clicksEntry.add(key, row.getClicks());
        impressionsEntry.add(key, row.getImpressions());
        ctrEntry.add(key, row.getCtr());
        positionEntry.add(key, row.getPosition());
      }

      return Set.of(
          clicksEntry,
          impressionsEntry,
          positionEntry,
          ctrEntry
            .setAverage(clicksEntry.getSum() / impressionsEntry.getSum() * 100));
    }
    return null;
  }
}
