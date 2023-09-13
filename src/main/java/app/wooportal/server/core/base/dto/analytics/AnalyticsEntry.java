package app.wooportal.server.core.base.dto.analytics;

import java.util.Objects;
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
  
  public AnalyticsEntry setName(String name) {
    this.name = name;
    return this;
  }
  
  public AnalyticsEntry setValue(Double value) {
    this.value = value;
    return this;
  }
  
  public AnalyticsEntry addValue(Double value) {
    if (this.value != null && value != null) {
      this.value += value;
    }
    return this;
  }
  
  @Override
  public int hashCode() {
    return getName() != null
        ? Objects.hash(getName())
        : super.hashCode();
  }
  
  @Override
  public boolean equals(Object other) {
    if (!getClass().equals(other.getClass())) {
      return false;
    }
    
    return getName() != null
        ? getName().equals(((AnalyticsDto) other).getName())
            || getName() == ((AnalyticsDto) other).getName()
        : super.equals(other);
  }
  
  @Override
  public int compareTo(AnalyticsEntry other) {
    return String.format("%100s", getName()).compareTo(
      String.format("%100s", other.getName()));
  }

}
