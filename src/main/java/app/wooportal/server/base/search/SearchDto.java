package app.wooportal.server.base.search;

import app.wooportal.server.base.cms.feature.FeatureEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class SearchDto {

  private String id;

<<<<<<< Upstream, based on main
  private FeatureEntity feature;
=======
  private SearchResultType type;
>>>>>>> 21479ea #498 searchApi and testData

}
