package app.wooportal.server.test.units.core.base.predicatebuilder;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import com.querydsl.core.types.Ops;
import app.wooportal.server.test.units.core.setup.entities.base.TestPredicateBuilder;
import app.wooportal.server.test.units.core.setup.services.QueryDslAssertion;

public class PredicateBuilderWithIdTest {
  
  private TestPredicateBuilder testBuilder = new TestPredicateBuilder();
  
  @Test
  public void withIdOk() throws Exception {       
    var test = "test";
    
    var result = testBuilder.withId(test);
    
    assertThat(result).isNotNull();
    QueryDslAssertion.assertOperator(Ops.EQ, result);
    QueryDslAssertion.assertPath("id", result);
    QueryDslAssertion.assertValue("test", result);
  }

}
