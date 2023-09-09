package app.wooportal.server.core.base.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnalyticsEntry implements Comparable<AnalyticsEntry> {
  
  private String name;
  
  private Double value; 
  
  @Override
  public int compareTo(AnalyticsEntry other) {
    return String.format("%100s", this.getName()).compareTo(
      String.format("%100s", other.getName()));
  }
  
  public AnalyticsEntry setName(String name) {
    this.name = name;
    return this;
  }
  
  public AnalyticsEntry setValue(Double value) {
    this.value = value;
    return this;
  }

}
