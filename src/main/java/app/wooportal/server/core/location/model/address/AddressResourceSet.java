package app.wooportal.server.core.location.model.address;

import java.util.List;

import lombok.Data;

@Data
public class AddressResourceSet {

  private Integer estimatedTotal;
  private List<AddressResource> resources;
  
}
