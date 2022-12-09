package app.wooportal.server.test.units.core.setup.entities.base;

import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.test.units.core.setup.entities.child.TestChildService;
import app.wooportal.server.test.units.core.setup.entities.listChild.TestListChildService;

public class TestService extends DataService<TestEntity, TestPredicateBuilder> {

  public TestService(
      DataRepository<TestEntity> repo, 
      TestPredicateBuilder entities) {
    super(repo, entities);
  }
  
  public TestService(
      DataRepository<TestEntity> repo, 
      TestPredicateBuilder entities,
      TestChildService childService,
      TestListChildService listChildService) throws Exception {
    super(repo, entities);
    
    setService("child", childService);
    setService("childs", listChildService);
    setService("oneToOneChild", childService);
  }

  public void setService(String field, DataService<?, ?> service) 
      throws Exception {
    addService(field, service);
  }
  
  @Override
  protected void preSave(TestEntity entity, TestEntity newEntity, JsonNode context) {
    removeContext("removeContextField", context);
    
    newEntity.setSetContext("test");
    setContext("setContext", context);
  }
  
}
