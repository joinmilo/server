package app.wooportal.server.test.units.core.base.predicatebuilder;

import java.util.List;

import org.junit.jupiter.api.Test;

import app.wooportal.server.test.units.core.setup.entities.base.TestEntity;
import app.wooportal.server.test.units.core.setup.entities.base.TestPredicateBuilder;
import app.wooportal.server.test.units.core.setup.entities.child.TestChildEntity;
import app.wooportal.server.test.units.core.setup.entities.listChild.TestListChildEntity;
import app.wooportal.server.test.units.core.setup.services.QueryDslAssertion;
//TODO: Fix examples 
public class PredicateBuilderWithExampleTest {
  
  private TestPredicateBuilder testBuilder = new TestPredicateBuilder();
  
  @Test
  public void withExampleOk() throws Exception {       
    var name = "test";
    var test = newTestEntity(name, null, null);
    
    var result = testBuilder.withExample(test);
    
    QueryDslAssertion.assertExample(result, test);
  }
  
  @Test
  public void withExampleChildFieldOk() throws Exception {
    var test = newTestEntity(
        "test", 
        newTestChildEntity("test"), 
        null); 

    var result = testBuilder.withExample(test);
    
    QueryDslAssertion.assertExample(result, test);
  }

  private TestEntity newTestEntity(
      String name,
      TestChildEntity child,
      List<TestListChildEntity> childs) {
    var entity = new TestEntity();
    entity.setName(name);
    entity.setChild(child);
    entity.setChilds(childs);
    return entity;
  }

  private TestChildEntity newTestChildEntity(
      String name) {
    var entity = new TestChildEntity();
    entity.setName(name);
    return entity;
  }
}
