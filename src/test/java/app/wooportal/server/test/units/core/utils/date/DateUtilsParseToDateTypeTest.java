package app.wooportal.server.test.units.core.utils.date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import app.wooportal.server.core.utils.DateUtils;

public class DateUtilsParseToDateTypeTest {
  
  @Test
  public void parseOffsetDateTime() throws Exception {
    var value = "2021-04-26T10:24:33+02:00";
    var type = OffsetDateTime.class;

    var result = DateUtils.parseToDateType(value, type);
    
    assertThat(result).isEqualTo(OffsetDateTime.parse(value));
  }
  
  @Test
  public void parseLocalDateTime() throws Exception {
    var value = "2021-04-26T10:24:33";
    var type = LocalDateTime.class;

    var result = DateUtils.parseToDateType(value, type);
    
    assertThat(result).isEqualTo(LocalDateTime.parse(value));
  }
  
  @Test
  public void parseLocalDate() throws Exception {
    var value = "2021-04-26";
    var type = LocalDate.class;

    var result = DateUtils.parseToDateType(value, type);
    
    assertThat(result).isEqualTo(LocalDate.parse(value));
  }
  
  @Test
  public void parseNoDate() throws Exception {
    var value = "parseNoDate";
    var type = String.class;

    var result = DateUtils.parseToDateType(value, type);
    
    assertThat(result).isEqualTo(value);
  }
  
  @Test
  public void parseNull() throws Exception {
    var type = String.class;

    var result = DateUtils.parseToDateType(null, type);
    
    assertThat(result).isNull();
  }
  
  @Test
  public void getAnnotationWithObjectBadParams() throws Exception {
    var value = "2021-04-26";
    
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> DateUtils.parseToDateType(value, null)));
    
    assertThat(result).allMatch(t -> t instanceof IllegalArgumentException);
  }

}
