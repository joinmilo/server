package app.wooportal.server.base.analytics.googleSearch.dto;

import java.util.ArrayList;
import app.wooportal.server.core.base.dto.analytics.AnalyticsEntry;
import lombok.Data;

@Data
public class SearchConsoleDetailsDto {
    
  private String name;
  
  private Double total = 0.0;
  
  private ArrayList<AnalyticsEntry> series;
   
}
