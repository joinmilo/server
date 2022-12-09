package app.wooportal.server.test.units.core.utils.date;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.base.dto.analytics.IntervalFilter;
import app.wooportal.server.core.utils.DateUtils;
import app.wooportal.server.test.units.core.utils.date.setup.DateUtilTestEntity;

public class DateUtilsGeneratePeriodContainerTest {
  
  @Test
  public void generatePeriodContainerMonthlyStart() throws Exception {
    var from = OffsetDateTime.of(2021, 5, 1, 1, 1, 1, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2021, 6, 1, 1, 1, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.generatePeriodContainer(
        from, end, IntervalFilter.MONTHLY, new DateUtilTestEntity());
    
    assertThat(result).containsKeys(
        "2021.05", 
        "2021.06");
    assertThat(result).allSatisfy((k, v) -> assertThat(v).isOfAnyClassIn(DateUtilTestEntity.class));
  }
  
  @Test
  public void generatePeriodContainerMonthlyNullValue() throws Exception {
    var from = OffsetDateTime.of(2021, 5, 1, 1, 1, 1, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2021, 6, 1, 1, 1, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.generatePeriodContainer(
        from, end, IntervalFilter.MONTHLY, null);
    
    assertThat(result).containsKeys(
        "2021.05", 
        "2021.06");
    assertThat(result).allSatisfy((k, v) -> assertThat(v).isNull());
  }
  
  @Test
  public void generatePeriodContainerMonthlyEnd() throws Exception {
    var from = OffsetDateTime.of(2021, 5, 30, 5, 1, 1, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2021, 6, 1, 1, 1, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.generatePeriodContainer(
        from, end, IntervalFilter.MONTHLY, new DateUtilTestEntity());
    
    assertThat(result).containsKeys("2021.05", "2021.06");
    assertThat(result).allSatisfy((k, v) -> assertThat(v).isOfAnyClassIn(DateUtilTestEntity.class));
  }
  
  @Test
  public void generatePeriodContainerCalendarWeekStart() throws Exception {
    var from = OffsetDateTime.of(2021, 5, 1, 1, 1, 1, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2021, 6, 1, 1, 1, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.generatePeriodContainer(
        from, end, IntervalFilter.CALENDAR_WEEKS, new DateUtilTestEntity());
    
    assertThat(result).containsKeys(
        "2021 KW 17", 
        "2021 KW 18",
        "2021 KW 19",
        "2021 KW 20",
        "2021 KW 21",
        "2021 KW 22");
    assertThat(result).allSatisfy((k, v) -> assertThat(v).isOfAnyClassIn(DateUtilTestEntity.class));
  }
  
  @Test
  public void generatePeriodContainerCalendarWeekEnd() throws Exception {
    var from = OffsetDateTime.of(2021, 5, 30, 5, 1, 1, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2021, 6, 1, 1, 1, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.generatePeriodContainer(
        from, end, IntervalFilter.CALENDAR_WEEKS, new DateUtilTestEntity());
    
    assertThat(result).containsKeys(
        "2021 KW 21", 
        "2021 KW 22");
    assertThat(result).allSatisfy((k, v) -> assertThat(v).isOfAnyClassIn(DateUtilTestEntity.class));
  }
  
  @Test
  public void generatePeriodContainerDailyStart() throws Exception {
    var from = OffsetDateTime.of(2021, 5, 1, 5, 1, 1, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2021, 5, 5, 1, 1, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.generatePeriodContainer(
        from, end, IntervalFilter.DAILY, new DateUtilTestEntity());
    
    assertThat(result).containsKeys(
        "2021.05.01", 
        "2021.05.02",
        "2021.05.03",
        "2021.05.04",
        "2021.05.05");
    assertThat(result).allSatisfy((k, v) -> assertThat(v).isOfAnyClassIn(DateUtilTestEntity.class));
  }
  
  @Test
  public void generatePeriodContainerDailyEnd() throws Exception {
    var from = OffsetDateTime.of(2021, 5, 30, 5, 1, 1, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2021, 6, 1, 1, 1, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.generatePeriodContainer(
        from, end, IntervalFilter.DAILY, new DateUtilTestEntity());
    
    assertThat(result).containsKeys(
        "2021.05.30", 
        "2021.05.31",
        "2021.06.01");
    assertThat(result).allSatisfy((k, v) -> assertThat(v).isOfAnyClassIn(DateUtilTestEntity.class));
  }
  
  @Test
  public void generatePeriodContainerHourlyStart() throws Exception {
    var from = OffsetDateTime.of(2021, 5, 1, 1, 1, 1, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2021, 5, 1, 5, 1, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.generatePeriodContainer(
        from, end, IntervalFilter.HOURLY, new DateUtilTestEntity());
    
    assertThat(result).containsKeys(
        "2021.05.01 01:00", 
        "2021.05.01 02:00",
        "2021.05.01 03:00",
        "2021.05.01 04:00",
        "2021.05.01 05:00");
    assertThat(result).allSatisfy((k, v) -> assertThat(v).isOfAnyClassIn(DateUtilTestEntity.class));
  }
  
  @Test
  public void generatePeriodContainerHourlyEnd() throws Exception {
    var from = OffsetDateTime.of(2021, 5, 1, 23, 1, 1, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2021, 5, 2, 2, 1, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.generatePeriodContainer(
        from, end, IntervalFilter.HOURLY, new DateUtilTestEntity());
    
    assertThat(result).containsKeys(
        "2021.05.01 23:00", 
        "2021.05.02 00:00",
        "2021.05.02 01:00",
        "2021.05.02 02:00");
    assertThat(result).allSatisfy((k, v) -> assertThat(v).isOfAnyClassIn(DateUtilTestEntity.class));
  }
  
  @Test
  public void generatePeriodContainerMinuteStart() throws Exception {
    var from = OffsetDateTime.of(2021, 5, 1, 1, 30, 1, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2021, 5, 1, 1, 34, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.generatePeriodContainer(
        from, end, IntervalFilter.MINUTE, new DateUtilTestEntity());
    
    assertThat(result).containsKeys(
        "2021.05.01 01:30", 
        "2021.05.01 01:31",
        "2021.05.01 01:32",
        "2021.05.01 01:33",
        "2021.05.01 01:34");
    assertThat(result).allSatisfy((k, v) -> assertThat(v).isOfAnyClassIn(DateUtilTestEntity.class));
  }
  
  @Test
  public void generatePeriodContainerMinuteEnd() throws Exception {
    var from = OffsetDateTime.of(2021, 5, 1, 1, 59, 1, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2021, 5, 1, 2, 2, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.generatePeriodContainer(
        from, end, IntervalFilter.MINUTE, new DateUtilTestEntity());
    
    assertThat(result).containsKeys(
        "2021.05.01 01:59", 
        "2021.05.01 02:00",
        "2021.05.01 02:01");
    assertThat(result).allSatisfy((k, v) -> assertThat(v).isOfAnyClassIn(DateUtilTestEntity.class));
  }

}
