package app.milo.server.core.location.model.address;

import java.util.List;

import lombok.Data;

@Data
public class Point {

  private String type;
  private List<Float> coordinates;

  public float getLatitude() {
    return coordinates.get(0);
  }

  public float getLongitude() {
    return coordinates.get(1);
  }

}
