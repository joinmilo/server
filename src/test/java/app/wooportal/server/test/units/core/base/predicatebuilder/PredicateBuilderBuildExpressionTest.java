package app.wooportal.server.test.units.core.base.predicatebuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.base.dto.query.ConjunctionOperator;
import app.wooportal.server.core.base.dto.query.QueryConjunction;
import app.wooportal.server.core.base.dto.query.QueryEntity;
import app.wooportal.server.core.base.dto.query.QueryExpression;
import app.wooportal.server.core.base.dto.query.QueryOperator;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.test.units.core.setup.entities.base.TestPredicateBuilder;
import app.wooportal.server.test.units.core.setup.services.QueryDslAssertion;

public class PredicateBuilderBuildExpressionTest {
  
  private TestPredicateBuilder testBuilder = new TestPredicateBuilder();
  
  @Test
  public void buildExpressionEntityField() throws Exception {       
    var query = newQueryEypression(
        newEntityQuery(QueryOperator.EQUAL, "name", "test"), null);
    
    var result = testBuilder.buildExpression(query);
    
    QueryDslAssertion.assertQuery(query, result);
  }
  
  @Test
  public void buildExpressionEntityDateField() throws Exception {       
    var query = newQueryEypression(
        newEntityQuery(QueryOperator.EQUAL, "created", "2021-04-26T10:28:31+02:00"), null);
    
    var result = testBuilder.buildExpression(query);
    
    QueryDslAssertion.assertQuery(query, result);
  }
  
  @Test
  public void buildExpressionEntityChild() throws Exception {
    var query = newQueryEypression(
        newEntityQuery(QueryOperator.GREATER_THAN, "child.name", "test"), null);
    
    var result = testBuilder.buildExpression(query);
    
    QueryDslAssertion.assertQuery(query, result);
  }
  
  @Test
  public void buildExpressionEntityChildList() throws Exception {
    var query = newQueryEypression(
        newEntityQuery(QueryOperator.GREATER_OR_EQUAL, "childs.name",  "test"), null);

    var result = testBuilder.buildExpression(query);
    
    QueryDslAssertion.assertQuery(query, result);
  }

  @Test
  public void buildExpressionConjunction() throws Exception {
    var query = newQueryEypression(
        null,
        newConjunctionQuery(
            ConjunctionOperator.AND, 
            List.of(
                newQueryEypression(
                    newEntityQuery(QueryOperator.LESS_THAN, "childs.name",  "test"),
                    null),
                newQueryEypression(
                    newEntityQuery(QueryOperator.LESS_OR_EQUAL, "name",  "test"),
                    null)
            )
        )
    );
    
    var result = testBuilder.buildExpression(query);
    
    QueryDslAssertion.assertQuery(query, result);
  }
  
  @Test
  public void buildExpressionConjunctionNested() throws Exception {
    var query = newQueryEypression(
        null,
        newConjunctionQuery(
            ConjunctionOperator.AND, 
            List.of(
                newQueryEypression(newEntityQuery(QueryOperator.EQUAL, "name",  "test"), null),
                newQueryEypression(
                    null, 
                    newConjunctionQuery(
                        ConjunctionOperator.OR, 
                        List.of(
                            newQueryEypression(newEntityQuery(QueryOperator.NOT_EQUAL, "childs.name",  "test"), null),
                            newQueryEypression(newEntityQuery(QueryOperator.EQUAL, "child.name",  "test"), null)))))));
    
    var result = testBuilder.buildExpression(query);
    
    QueryDslAssertion.assertQuery(query, result);
  }
  
  @Test
  public void buildExpressionPathNotExists() throws Exception {       
    var query = newQueryEypression(
        newEntityQuery(QueryOperator.EQUAL, "fieldNotExists", "test"), null);
    
    var result = catchThrowable(() -> testBuilder.buildExpression(query));
    
    assertThat(result).isInstanceOf(BadParamsException.class);
  }
  
  @Test
  public void buildExpressionWithBadParams() throws Exception {
    var result = new ArrayList<Throwable>();
    result.add(catchThrowable(() -> testBuilder.buildExpression(newQueryEypression(null, null))));
    result.add(catchThrowable(() -> testBuilder.buildExpression(
        newQueryEypression(null, newConjunctionQuery(null, null)))));
    result.add(catchThrowable(() -> testBuilder.buildExpression(
        newQueryEypression(null, newConjunctionQuery(null, new ArrayList<QueryExpression>())))));
    
    assertThat(result).allMatch(t -> t instanceof BadParamsException);
  }
  
  private QueryExpression newQueryEypression(
      QueryEntity entity,
      QueryConjunction conjunction) {
    var expression = new QueryExpression();
    expression.setEntity(entity);
    expression.setConjunction(conjunction);
    return expression;
  }
  
  private QueryConjunction newConjunctionQuery(
      ConjunctionOperator operator, 
      List<QueryExpression> operands) {
    var query = new QueryConjunction();
    query.setOperator(operator);
    query.setOperands(operands);
    return query;
  }

  private QueryEntity newEntityQuery(QueryOperator operator, String path, String value) {
    var query = new QueryEntity();
    query.setOperator(operator);
    query.setPath(path);
    query.setValue(value);
    return query;
  }

}
