package app.wooportal.server.core.location.dto;

import lombok.Data;

@Data
public class Coordinate {

  private Double latitude;
  
  private Double longitude;

  @Override
  public String toString() {
    return latitude + "," + longitude;
  }
  
  
}
