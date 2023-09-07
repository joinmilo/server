package app.wooportal.server.base.analytics.googleSearch;

import app.wooportal.server.core.base.dto.analytics.AnalyticsEntry;
import lombok.Data;

@Data
public class GoogleSearchDto {
    
  private String name;
  
  private Double value = 0.0;
  
  private AnalyticsEntry entry;
   
}
