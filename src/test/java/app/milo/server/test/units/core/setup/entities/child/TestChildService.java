package app.milo.server.test.units.core.setup.entities.child;

import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

public class TestChildService extends DataService<TestChildEntity, TestChildPredicateBuilder> {

  public TestChildService(
      DataRepository<TestChildEntity> repo, 
      TestChildPredicateBuilder entities) {
    super(repo, entities);
  }

}
