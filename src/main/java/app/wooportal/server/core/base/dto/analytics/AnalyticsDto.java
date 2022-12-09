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
  
  private TreeSet<AnalyticsEntry> series = new TreeSet<>();
  
  public AnalyticsDto(String name, Map<String, Double> series) {
    setName(name);
    setSeries(series);
  }
  
  public AnalyticsDto(String name, Collection<AnalyticsEntry> series) {
    setName(name);
    addAll(series);
  }
  
  public AnalyticsDto add(AnalyticsEntry entry) {
    this.series.add(entry);
    return this;
  }
  
  public AnalyticsDto addAll(Collection<AnalyticsEntry> series) {
    this.series.addAll(series);
    return this;
  }

  public AnalyticsDto setSeries(Map<String, Double> entries) {
    if (entries != null) {
      entries.forEach((k, v) -> series.add(new AnalyticsEntry(k,v))); 
    }
    return this;
  }
  
  @Override
  public int compareTo(AnalyticsDto other) {
    return this.getName().compareTo(other.getName());
  }
}
