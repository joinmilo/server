package app.wooportal.server.base.analytics.googleSearch.dto;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class SearchConsoleSummaryDto {
    
  private Integer totalClicks = 0;
  
  private Integer totalImpressions = 0;
  
  @JsonIgnore
  private List<Double> positions = new ArrayList<Double>();
  
  @JsonIgnore
  private List<Double> ctrs = new ArrayList<Double>();

  public void addClicks(int clicks) {
    totalClicks = totalClicks + clicks;
  }

  public void addImpressions(int impressions) {
    totalImpressions = totalImpressions + impressions;
  }

  public void addPosition(Double position) {
    positions.add(position);
  }
  
  public void addCtr(Double ctr) {
    ctrs.add(ctr);
  }
  
  public Double getAveragePosition() {
    return positions.stream().reduce(0.0, Double::sum) / positions.size();
  }
  
  public Double getAverageCtr() {
    return ctrs.stream().reduce(0.0, Double::sum) / ctrs.size();
  }
  
}
