package app.wooportal.server.test.units.core.analytics;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.base.dto.analytics.AnalyticsDto;
import app.wooportal.server.core.base.dto.analytics.AnalyticsEntry;

public class DtoTest {
  
  @Test
  public void createWithMapOk() {
    var name = "name";
    var series = Map.of(
        "1", 0.0, 
        "2", 1.0,
        "3", 2.0);
    
    var result = new AnalyticsDto(name, series);
    
    assertThat(result.getName()).isEqualTo(name);
    assertThat(result.getSeries()).allMatch(s ->
      series.containsKey(s.getName()) && series.containsValue(s.getValue()) 
    );
  }
  
  @Test
  public void createWithAnalyticsEntryOk() {
    var name = "name";
    var series = List.of(
        new AnalyticsEntry("1", 0.0),
        new AnalyticsEntry("2", 1.0),
        new AnalyticsEntry("3", 2.0));
    
    var result = new AnalyticsDto(name, series);
    
    assertThat(result.getName()).isEqualTo(name);
    assertThat(result.getSeries()).allMatch(s -> series.contains(s));
  }
  
  @Test
  public void addOk() {
    var entry = new AnalyticsEntry("1", 0.0);
    
    var result = new AnalyticsDto().add(entry);
    
    assertThat(result.getSeries()).anyMatch(s -> entry.equals(s));
  }
  
  @Test
  public void addAllOk() {
    var series = List.of(
        new AnalyticsEntry("1", 0.0),
        new AnalyticsEntry("2", 1.0),
        new AnalyticsEntry("3", 2.0));
    
    var result = new AnalyticsDto().addAll(series);
    
    assertThat(result.getSeries()).allMatch(s -> series.contains(s));
  }
  
  @Test
  public void setSeriesWithNull() {    
    var result = new AnalyticsDto().setSeries(null);
    
    assertThat(result.getSeries()).isEmpty();
  }

}
