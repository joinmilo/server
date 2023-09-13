package app.wooportal.server.test.units.core.utils.date;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.base.dto.analytics.IntervalFilter;
import app.wooportal.server.core.utils.DateUtils;

public class DateUtilsFormatTest {
  
  @Test
  public void formatMonthly() throws Exception {
    var test = OffsetDateTime.of(2021, 5, 1, 1, 1, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.format(test, IntervalFilter.MONTHLY);
    
    assertThat(result).isEqualTo("2021.05");
  }
  
  @Test
  public void formatCalendarWeeks() throws Exception {
    var test = OffsetDateTime.of(2021, 5, 1, 1, 1, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.format(test, IntervalFilter.CALENDAR_WEEKS);
    
    assertThat(result).isEqualTo("2021 KW 17");
  }
  
  @Test
  public void formatDaily() throws Exception {
    var test = OffsetDateTime.of(2021, 5, 1, 1, 1, 1, 0, ZoneOffset.UTC);

    var result = DateUtils.format(test, IntervalFilter.DAILY);
    
    assertThat(result).isEqualTo("2021.05.01");
  }

}
