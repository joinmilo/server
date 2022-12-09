package app.wooportal.server.test.units.core.setup.entities.listChild;

import java.util.Optional;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

public class TestListChildService extends DataService<TestListChildEntity, TestListChildPredicateBuilder> {

  public TestListChildService(
      DataRepository<TestListChildEntity> repo, 
      TestListChildPredicateBuilder entities) {
    super(repo, entities);
  }
  
  @Override
  public Optional<TestListChildEntity> getExisting(TestListChildEntity listChild) {
    return listChild.getName() == null || listChild.getName().isBlank()
        ? Optional.empty()
        : repo.findOne(singleQuery(predicate.withName(listChild.getName())));
  }

}
