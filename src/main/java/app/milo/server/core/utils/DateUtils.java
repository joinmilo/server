package app.milo.server.core.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import app.milo.server.core.base.dto.analytics.IntervalFilter;

public class DateUtils {
  
  public static <T> Map<String, T> generatePeriodContainer(
      OffsetDateTime fromDate, 
      OffsetDateTime endDate,
      IntervalFilter interval) {
    return generatePeriodContainer(fromDate, endDate, interval, null);
  }
  
  public static <T> Map<String, T> generatePeriodContainer(
      OffsetDateTime fromDate, 
      OffsetDateTime endDate,
      IntervalFilter interval,
      T template) {
    Map<String, T> container = new HashMap<>();
    for (OffsetDateTime date = start(fromDate, interval); date.isBefore(endDate) || date.isEqual(endDate); 
        date = date.plus(1, getUnit(interval))) {
      String key = date.format(getFormatter(interval));
      container.put(key, template == null ? null : ReflectionUtils.newInstance(template));
    }
    return container;
  }

  private static OffsetDateTime start(OffsetDateTime date, IntervalFilter interval) {
    var start = date.withSecond(0);
    switch (interval) {
      case DAILY:
        start = start.withMinute(0);
        return start.withHour(0);
      case CALENDAR_WEEKS:
        start = start.withMinute(0);
        start = start.withHour(0);
        return start.with(DayOfWeek.MONDAY);
      case MONTHLY:
        start = start.withMinute(0);
        start = start.withHour(0);
        return start.withDayOfMonth(1);
      default:
        return start;
    }
  }

  private static TemporalUnit getUnit(IntervalFilter interval) {
    return switch (interval) {
      case MONTHLY -> ChronoUnit.MONTHS;
      case CALENDAR_WEEKS -> ChronoUnit.WEEKS;
      case DAILY -> ChronoUnit.DAYS;
      default -> ChronoUnit.DAYS;
    };
  }
  
  public static String formatLocalDate(String date, DateTimeFormatter sourceFormatter, IntervalFilter interval) {
    return LocalDate
        .parse(date, sourceFormatter)
        .format(DateUtils.getFormatter(interval));
  }
  
  public static String format(OffsetDateTime date, IntervalFilter interval) {
    return date.format(DateUtils.getFormatter(interval));
  }
  
  public static DateTimeFormatter getFormatter(IntervalFilter interval) {
    String pattern = switch (interval) {
      case MONTHLY -> "yyyy.MM";
      case CALENDAR_WEEKS -> "YYYY 'KW' ww";
      case DAILY -> "yyyy.MM.dd";
      default -> "yyyy.MM.dd HH:00";
    };

    return DateTimeFormatter.ofPattern(pattern, Locale.GERMANY);
  }
  
  public static Object parseToDateType(Object value, Class<?> type) {
    if (type == null) {
      throw new IllegalArgumentException("type must not be null");
    }
    
    if (value == null) {
      return value;
    }
    
    if (type.isAssignableFrom(OffsetDateTime.class)) {
      return OffsetDateTime.parse(value.toString());
    }
    
    if (type.isAssignableFrom(LocalDateTime.class)) {
      return LocalDateTime.parse(value.toString());
    }
    
    if (type.isAssignableFrom(LocalDate.class)) {
      return LocalDate.parse(value.toString());
    }
    
    return value;
  }
}
