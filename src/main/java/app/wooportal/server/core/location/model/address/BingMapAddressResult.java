package app.wooportal.server.core.location.model.address;

import java.util.List;

import app.wooportal.server.core.location.model.BingMapResult;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class BingMapAddressResult extends BingMapResult {

  private List<AddressResourceSet> resourceSets;
}
