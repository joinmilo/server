package app.wooportal.server.test.units.core.setup.services;

import static org.assertj.core.api.Assertions.assertThat;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.assertj.core.api.ObjectAssert;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Operation;
import com.querydsl.core.types.Operator;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.PathImpl;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.PredicateOperation;
import com.querydsl.core.types.dsl.BooleanOperation;
import app.wooportal.server.core.base.dto.query.QueryConjunction;
import app.wooportal.server.core.base.dto.query.QueryEntity;
import app.wooportal.server.core.base.dto.query.QueryExpression;
import app.wooportal.server.core.utils.ReflectionUtils;

public class QueryDslAssertion {

  public static void assertQuery(QueryExpression expression, Expression<?> result) {
    if (expression.getEntity() != null) {
      assertQueryEntity(expression.getEntity(), result);
    }
    
    if (expression.getConjunction() != null) {
      assertQueryConjunction(expression.getConjunction(), result);
    }
  }
  
  public static void assertQueryEntity(QueryEntity query, Expression<?> result) {
    assertThat(result).isOfAnyClassIn(BooleanOperation.class);
    assertOperator(query, result);
    assertPath(query, (BooleanOperation) result);
    assertValue(query, (BooleanOperation) result);
  }
  
  public static void assertQueryConjunction(QueryConjunction query, Expression<?> result) {
    if (result instanceof BooleanBuilder) {
      assertOperator(query, ((BooleanBuilder) result).getValue());
      assertOperands(query, ((BooleanBuilder) result).getValue());
    } else {
      assertOperator(query, result);
      assertOperands(query, result);
    }
  }
  
  public static void assertPredicate(Expression<?> result, Object source) {
    var operation = getOperation(result);
    if (operation != null) {
      for (var arg : operation.getArgs()) {
        if (arg instanceof PredicateOperation) {
          assertPredicate((Operation<?>) arg, source);
        }
        
        if (arg instanceof PathImpl<?> || arg instanceof ConstantImpl<?>) {
          assertMatch((Ops) operation.getOperator(), operation.getArgs(), source);
          break;
        }
      } 
    }
  }
  
  private static Operation<?> getOperation(Expression<?> result) {
    if (result instanceof BooleanBuilder) {
      var predicate = ((BooleanBuilder) result).getValue();
      return (predicate instanceof BooleanBuilder) 
          ? (Operation<?>) ((BooleanBuilder) predicate).getValue()
          : (Operation<?>) predicate;
    }
    
    return (Operation<?>) result;
  }
  
  private static void assertMatch(Ops operator, List<Expression<?>> args, Object source) {
    List<String> path = null;
    Object pathValue = null;
    for (var arg : args) {
      if (arg instanceof PathImpl<?>) {
        path = new ArrayList<>(List.of(((PathImpl<?>) arg)
            .toString()
            .replace("any(", "")
            .replace(")", "")
            .split("\\.")));
      }
      if (arg instanceof ConstantImpl<?>) {
        pathValue = ((ConstantImpl<?>) arg).getConstant();
      }
    }

    assertThat(pathValue).isNotNull();
    assertThat(matchFieldValue(
        operator, path.subList(1, path.size()), source, pathValue))
      .isTrue();
  }

  private static boolean matchFieldValue(
      Ops operator,
      List<String> path, 
      Object source,
      Object pathValue) {
    for (Field field : getFields(source)) {
      if (field.getName().equals(path.get(0))) {
        try {
          field.setAccessible(true);
          var fieldValue = field.get(source);
          if (fieldValue instanceof Collection<?>) {
            for (Object element : ((Collection<?>) fieldValue)) {
              if (matchFieldValue(
                  operator,
                  path.subList(1, path.size()), 
                  element,
                  pathValue)) {
                return true;
              }
            }
            return false;
          }
          return path.size() > 1
              ? matchFieldValue(
                  operator,
                  path.subList(1, path.size()), 
                  fieldValue, 
                  pathValue)
              : matchWithOperator(operator, fieldValue, pathValue);
        } catch(Exception e) { }
      }
    }
    return false;
  }

  private static boolean matchWithOperator(Ops operator, Object fieldValue, Object pathValue) {
    switch(operator) {
      case LIKE:
      case LIKE_ESCAPE:
      case LIKE_ESCAPE_IC:
      case LIKE_IC:
        return fieldValue.toString().contains(pathValue.toString().replace("%", ""));
      case EQ_IGNORE_CASE:
        return fieldValue != null && ((String) fieldValue).equalsIgnoreCase((String) pathValue);
      default:
        return fieldValue != null && fieldValue.equals(pathValue);
    }
  }

  public static void assertExample(Predicate result, Object source) {
    assertThat(result).isOfAnyClassIn(BooleanBuilder.class);
    assertOperator(((BooleanBuilder) result).getValue() instanceof BooleanOperation
      ? Ops.EQ_IGNORE_CASE
      : Ops.AND, result);
    
    for (Field field : getFields(source)) {
      try {
        field.setAccessible(true);
        var value = field.get(source);
        if (!field.getName().equals("serialVersionUID") && value != null) {
          if (ReflectionUtils.isBaseType(value.getClass())) {
            assertPath(field.getName(), result);
            assertValue(value.toString(), result);
          } else {
            assertExample(result, value);
          }
        }
      } catch (IllegalArgumentException | IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      
    }
  }
  
  private static List<Field> getFields(Object source) {
    var fields = new ArrayList<Field>();
    fields.addAll(List.of(source.getClass().getDeclaredFields()));
    fields.addAll(List.of(source.getClass().getSuperclass().getDeclaredFields()));
    return fields;
  }

  private static void assertOperands(QueryConjunction query, Expression<?> value) {
    assertThat(value).isOfAnyClassIn(PredicateOperation.class);
    for (int i = 0; i < query.getOperands().size(); i++) {
      if (query.getOperands().get(i).getEntity() != null) {
        assertPath(
            query.getOperands().get(i).getEntity(), 
            (Operation<?>) ((PredicateOperation) value).getArgs().get(i));
        assertValue(
            query.getOperands().get(i).getEntity(), 
            (Operation<?>) ((PredicateOperation) value).getArgs().get(i));
      }
      if (query.getOperands().get(i).getConjunction() != null) {
        assertQuery(
            query.getOperands().get(i), 
            ((PredicateOperation) value).getArgs().get(i));
      }
    }
  }
  
  public static void assertOperator(QueryEntity query, Expression<?> result) {
    switch(query.getOperator()) {
      case GREATER_THAN:
        assertThat(((Operation<?>) result).getOperator()).isEqualTo(Ops.GT);
        break;
      case GREATER_OR_EQUAL:
        assertThat(((Operation<?>) result).getOperator()).isEqualTo(Ops.GOE);
        break;
      case LESS_THAN:
        assertThat(((Operation<?>) result).getOperator()).isEqualTo(Ops.LT);
        break;
      case LESS_OR_EQUAL:
        assertThat(((Operation<?>) result).getOperator()).isEqualTo(Ops.LOE);
        break;
      case LIKE:
        assertThat(((Operation<?>) result).getOperator()).isIn(Ops.LIKE_IC, Ops.LIKE);
        break;
      case NOT_EQUAL:
        assertThat(((Operation<?>) result).getOperator()).isEqualTo(Ops.NE);
        break;
      case EQUAL:
        assertThat(((Operation<?>) result).getOperator()).isIn(Ops.EQ_IGNORE_CASE, Ops.EQ);
        break;
      default:
        assertThat(true).isFalse();
    }
  }
  
  public static void assertOperator(QueryConjunction query, Expression<?> result) {
    switch(query.getOperator()) {
      case AND_NOT:
        assertThat(((Operation<?>) result).getOperator()).isEqualTo(Ops.NOT);
        break;
      case OR_NOT:
        assertThat(((Operation<?>) result).getOperator()).isEqualTo(Ops.GOE);
        break;
      case AND:
        assertThat(((Operation<?>) result).getOperator()).isEqualTo(Ops.AND);
        break;
      case OR:
        assertThat(((Operation<?>) result).getOperator()).isEqualTo(Ops.OR);
        break;
      default:
        assertThat(true).isFalse();
    }
  }
  
  public static ObjectAssert<Operator> assertOperator(Ops queryDslOperator, Expression<?> result) {
    return (result instanceof BooleanBuilder)
      ? assertThat(((Operation<?>) ((BooleanBuilder) result).getValue()).getOperator()).isEqualTo(queryDslOperator)
      : assertThat(((Operation<?>) result).getOperator()).isEqualTo(queryDslOperator);
  }

  public static void assertPath(QueryEntity query, Operation<?> result) {
    assertThat(result.getArgs()).anyMatch(arg -> {
      if (arg instanceof PathImpl<?>) {
        var paths = query.getPath().split("\\.");
        return ((PathImpl<?>) arg).getMetadata().getElement().equals(paths[paths.length - 1]);
      }
      return false;
    });
  }
  
  public static void assertPath(String path, Expression<?> result) {
    var args = (result instanceof BooleanBuilder)
        ? ((Operation<?>) ((BooleanBuilder) result).getValue()).getArgs()
        : ((Operation<?>) result).getArgs();
    
    assertThat(containsPath(path, args)).isTrue();
  }
  
  private static boolean containsPath(String path, List<Expression<?>> args) {
    for (var arg : args) {
      if (arg instanceof PredicateOperation) {
        if (containsPath(path, ((PredicateOperation) arg).getArgs())) {
          return true;
        }
      }
      if (arg instanceof PathImpl<?>) {
        return ((PathImpl<?>) arg).getMetadata().getElement().equals(path);
      }
    }
    return false;
  }

  public static void assertValue(QueryEntity query, Operation<?> result) {
    assertThat(result.getArgs()).anyMatch(arg -> {
      if (arg instanceof ConstantImpl<?>) {
        return ((ConstantImpl<?>) arg).getConstant().toString().equals(query.getValue());
      }
      return false;
    });
  }
  
  public static void assertValue(String value, Expression<?> result) {
    var args = (result instanceof BooleanBuilder)
        ? ((Operation<?>) ((BooleanBuilder) result).getValue()).getArgs()
        : ((Operation<?>) result).getArgs();
    
    assertThat(containsValue(value, args)).isTrue();
  }
  
  private static boolean containsValue(String value, List<Expression<?>> args) {
    for (var arg : args) {
      if (arg instanceof PredicateOperation) {
        if (containsValue(value, ((PredicateOperation) arg).getArgs())) {
          return true;
        }
      }
      if (arg instanceof ConstantImpl<?>) {
        return ((ConstantImpl<?>) arg).getConstant().toString().equals(value);
      }
    }
    return false;
  }

}
