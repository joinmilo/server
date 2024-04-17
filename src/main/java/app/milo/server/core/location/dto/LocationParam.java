package app.milo.server.core.location.dto;

import java.util.List;

import lombok.Data;

@Data
public class LocationParam {
  
  private TravelMode travelMode;
  
  private Coordinate startPoint;
  
  private Coordinate targetPoint;
  
  private List<Coordinate> via;
 
}
