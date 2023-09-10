package app.wooportal.server.core.base.dto.analytics;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AnalyticsDto implements Comparable<AnalyticsDto> {

  private String name;
  
  private Double summary = 0.0;
  
  private TreeSet<AnalyticsEntry> series = new TreeSet<>();
  
  public AnalyticsDto(
      String name,
      Map<String, Double> series) {
    setName(name);
    setSeries(series);
  }
  
  public AnalyticsDto(
      String name,
      Collection<AnalyticsEntry> series) {
    setName(name);
    addAll(series);
  }
  
  public AnalyticsDto setName(String name) {
    this.name = name;
    return this;
  }
  
  public AnalyticsDto addAll(Collection<AnalyticsEntry> series) {
    if (series != null) {      
      for (var entry : series) {      
        add(entry);
      }
    }
    return this;
  }
  
  public AnalyticsDto setSeries(Map<String, Double> entries) {
    if (entries != null) {
      entries.forEach((k, v) -> add(new AnalyticsEntry(k,v))); 
    }
    return this;
  }
  
  public AnalyticsDto add(String key, Double value) {
    if (key != null && value != null) {
      add(new AnalyticsEntry(key, value));
    }
    return this;
  }
  
  public AnalyticsDto add(AnalyticsEntry entry) {
    if (entry != null) {      
      this.series.add(entry);
      this.summary += entry.getValue();
    }
    return this;
  }
  
  public Double getAverageSummary() {
    return summary / series.size();
  }
  
  @Override
  public int compareTo(AnalyticsDto other) {
    return this.getName().compareTo(other.getName());
  }
}
