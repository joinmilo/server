package app.wooportal.server.core.base.dto.analytics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

//TODO: It's getting too complicated
// Better: AnalyticsService and Dto as container only
public class AnalyticsDto implements Comparable<AnalyticsDto> {
  
  @Getter(AccessLevel.NONE)
  private boolean allowNull = true;
  
  private Double average;
  
  @Getter(AccessLevel.NONE)
  private Double averageCalculated;

  private String name;
  
  private Double sum;
  
  @Getter(AccessLevel.NONE)
  private Double sumCalculated;
  
  private AnalyticsOperation entryOperation = AnalyticsOperation.SUM; 
  
  @Getter(AccessLevel.NONE)
  private TreeMap<String, List<AnalyticsEntry>> elements = new TreeMap<>();
  
  private TreeSet<AnalyticsEntry> series;
  
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
  
  @JsonIgnore
  public AnalyticsDto setAllowNull(boolean allowNull) {
    this.allowNull = allowNull;
    return this;
  }
  
  @JsonIgnore
  public AnalyticsDto setAverage(Double average) {
    if (!Double.isNaN(average)) {      
      this.average = average;
    }
    return this;
  }
  
  @JsonIgnore
  public AnalyticsDto setName(String name) {
    this.name = name;
    return this;
  }
  
  @JsonIgnore
  public AnalyticsDto setEntryOperation(AnalyticsOperation entryOperation) {
    this.entryOperation = entryOperation;
    return this;
  }
  
  public TreeSet<AnalyticsEntry> getSeries() {
    if (series == null) {
      compute();
      return series;
    }
    return series;
  }
  
  public Double getSum() {
    if (series == null) {
      compute();
    }
    
    return sum != null
        ? sum
        : sumCalculated;
  }
  
  @JsonIgnore
  public AnalyticsDto addAll(Collection<AnalyticsEntry> series) {
    if (series != null) {      
      for (var entry : series) {      
        add(entry);
      }
    }
    return this;
  }
  
  @JsonIgnore
  public AnalyticsDto setSeries(Map<String, Double> entries) {
    if (entries != null) {
      entries.forEach((k, v) -> add(new AnalyticsEntry(k,v))); 
    }
    return this;
  }
  
  @JsonIgnore
  public AnalyticsDto add(String key, Integer value) {
    if (value != null) {      
      return add(key, Double.valueOf(value));
    }
    return this;
  }
  
  @JsonIgnore
  public AnalyticsDto add(String key, Double value) {
    if (key != null && value != null) {
      add(new AnalyticsEntry(key, value));
    }
    return this;
  }
  
  @JsonIgnore
  public AnalyticsDto add(AnalyticsEntry entry) {
    if (entry != null) {
      if (elements.containsKey(entry.getName())) {
        elements.get(entry.getName())
          .add(entry);
      } else {
        elements.put(entry.getName(),
            new ArrayList<AnalyticsEntry>(List.of(entry)));
      }
    }
    return this;
  }
  
  public Double getAverage() {
    if (series == null) {
      compute();
    }
    
    return average != null
        ? average
        : averageCalculated;
  }
  
  @JsonIgnore
  public AnalyticsDto compute() {
    var result = new TreeSet<AnalyticsEntry>();
    var count = 0.0;
    sumCalculated = 0.0;
    if (elements != null && !elements.isEmpty()) {
      for (var entry: elements.entrySet()) {
        Double value = entry.getValue().size() > 0
            ? calculate(entry.getValue())
                : entry.getValue().get(0).getValue();
        sumCalculated += value;
        
        count = !allowNull
            ? value > 0.0
                ? count + 1
                : count
            : count + 1;

        result.add(new AnalyticsEntry(entry.getKey(), value));
      }
      
      this.series = result;
      this.averageCalculated = count != 0.0
          ? sumCalculated / count
          : 0;
    }
    
    return this;
  }
  
  @JsonIgnore
  private Double calculate(List<AnalyticsEntry> entries) {
    var values = entries.stream().mapToDouble(AnalyticsEntry::getValue);
    
    if (!allowNull) {
      values = values.filter(value -> value > 0.0);
    }
    
    return switch(entryOperation) {
      case AVG -> {
        var value = values.average();
        yield value.isPresent()
            ? value.getAsDouble()
            : 0.0;
      }
      case MAX -> {
        var value = values.max();
        yield value.isPresent()
            ? value.getAsDouble()
            : 0.0;
      }
      case MIN -> {
        var value = values.min();
        yield value.isPresent()
            ? value.getAsDouble()
            : 0.0;
      }
      case COUNT -> Double.valueOf(values.count());
      case SUM -> values.sum();
      default -> values.sum();
    };
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
  public int compareTo(AnalyticsDto other) {
    return this.getName().compareTo(other.getName());
  }
}
