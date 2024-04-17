
package app.milo.server.core.location.model.route;

import java.util.List;

import lombok.Data;

@Data
public class Line {

  public String type;
  public List<List<Double>> coordinates;

}
