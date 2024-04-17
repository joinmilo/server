package app.milo.server.test.units.core.utils.reflection.setup;

import app.milo.server.core.base.DataService;
import app.milo.server.core.base.PredicateBuilder;
import app.milo.server.core.repository.DataRepository;

public class TestDataService 
    extends DataService<ReflectionTestEntity, PredicateBuilder<?,ReflectionTestEntity>> {

  public TestDataService(
      DataRepository<ReflectionTestEntity> repo, 
      PredicateBuilder<?, ReflectionTestEntity> entities) {
    super(repo, entities);
  }

}
