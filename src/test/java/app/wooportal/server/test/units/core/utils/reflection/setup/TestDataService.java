package app.wooportal.server.test.units.core.utils.reflection.setup;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.base.PredicateBuilder;
import app.wooportal.server.core.repository.DataRepository;

public class TestDataService 
    extends DataService<ReflectionTestEntity, PredicateBuilder<?,ReflectionTestEntity>> {

  public TestDataService(
      DataRepository<ReflectionTestEntity> repo, 
      PredicateBuilder<?, ReflectionTestEntity> entities) {
    super(repo, entities);
  }

}
