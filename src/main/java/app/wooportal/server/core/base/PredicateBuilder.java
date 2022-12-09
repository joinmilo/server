package app.wooportal.server.core.base;

import java.util.Collection;
import org.hibernate.Hibernate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.CollectionPathBase;
import com.querydsl.core.types.dsl.ComparableExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.query.QueryExpression;
import app.wooportal.server.core.base.dto.query.QueryOperator;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.utils.DateUtils;
import app.wooportal.server.core.utils.PersistenceUtils;
import app.wooportal.server.core.utils.ReflectionUtils;

public abstract class PredicateBuilder<T extends EntityPathBase<?>, E extends BaseEntity> {
  
  protected T query;
  
  public PredicateBuilder(T query) {
    this.query = query;
  }
  
  public Predicate withId(String id) {
    if (id == null || id.isBlank()) {
      return null;
    }
    
    var result = ReflectionUtils.get("id", query).map(value -> ((StringPath) value).eq(id));
    if (result.isEmpty()) {
      throw new RuntimeException("Entity has no field id");
    }
    
    return result.get();
  }
  
  public Predicate withExample(E entity) {
    var builder = new BooleanBuilder();
    matchEntity(entity, query, builder);
    return builder;
  }

  @SuppressWarnings("unchecked")
  public void matchEntity(E entity, T currentQuery, BooleanBuilder builder) {
    for (var entityField : ReflectionUtils.getFields(entity.getClass())) {
      if (PersistenceUtils.isValidField(entityField)) {
        ReflectionUtils.get(entityField.getName(), entity).ifPresent(value ->
            ReflectionUtils.get(entityField.getName(), currentQuery).ifPresent(path -> {
              var fieldValue = Hibernate.unproxy(value);
              if (PersistenceUtils.isValidEntity(fieldValue)) {
                matchEntity((E) fieldValue,(T) path, builder);
              } else if (PersistenceUtils.isValidCollection(fieldValue)) {
                for (E subEntity : (Collection<E>) fieldValue) {
                  matchEntity(subEntity, (T) ((CollectionPathBase<?, ?, ?>) path).any(), builder);
                }
              } else {
                builder.and(createExpression((Expression<?>) path, QueryOperator.EQUAL, fieldValue));
              }
         }));
      }
    }
  }
  
  protected Predicate create(FilterSortPaginate params) {
    if (params == null 
        || params.getExpression() == null && (
            params.getSearch() == null || params.getSearch().isBlank())) {
      throw new IllegalArgumentException("Either search or expression must not be null");
    }
    
    return params.getSearch() != null && !params.getSearch().isBlank()
        ? freeSearch(params.getSearch())
        : buildExpression(params.getExpression());
  }

  public abstract BooleanExpression freeSearch(String term);
  
  public Predicate buildExpression(QueryExpression expression) {
    if (expression != null && expression.getEntity() != null) {
      return createExpression(
          createQueryPath(expression.getEntity().getPath()),
          expression.getEntity().getOperator(), 
          expression.getEntity().getValue());
    }
    
    if (expression != null
        && expression.getConjunction() != null 
        && expression.getConjunction().getOperands() != null
        && !expression.getConjunction().getOperands().isEmpty()) {
      var builder = new BooleanBuilder();
      for (QueryExpression subExpression : expression.getConjunction().getOperands()) {
        switch (expression.getConjunction().getOperator()) {
          case OR -> builder.or(buildExpression(subExpression));
          case AND_NOT -> builder.andNot(buildExpression(subExpression));
          case OR_NOT -> builder.orNot(buildExpression(subExpression));
          default -> builder.and(buildExpression(subExpression));
        }
      }
      return builder;
    }
    
    throw new BadParamsException(
        "Both query entity and query conjunction are null or empty operands", expression);
  }

  protected Expression<?> createQueryPath(String entityPath) {
    Object queryPath = query;
    for (String field: entityPath.split("\\.")) {
      if (ReflectionUtils.getField(queryPath.getClass(), field).isEmpty()) {
        throw new BadParamsException(
            "Field does not exist on " + queryPath.getClass().getName(), field);
      }
      var fieldPath = ReflectionUtils.get(field, queryPath);
      
      if (fieldPath.isPresent()) {
        queryPath = fieldPath.get(); 
      }
      
      if (queryPath instanceof CollectionPathBase<?, ?, ?>) {
        queryPath = ((CollectionPathBase<?, ?, ?>) queryPath).any();
      }
    }
    return (Expression<?>) queryPath;
  }
  
  @SuppressWarnings("unchecked")
  protected BooleanExpression createExpression(Expression<?> queryPath, QueryOperator operator, Object value) {
    value = DateUtils.parseToDateType(value, queryPath.getType());
    switch (operator) {
      case GREATER_THAN:
        return ((ComparableExpression<Comparable<?>>) queryPath).gt((Comparable<?>) value);
      case GREATER_OR_EQUAL:
        return ((ComparableExpression<Comparable<?>>) queryPath).goe((Comparable<?>) value);
      case LESS_THAN:
        return ((ComparableExpression<Comparable<?>>) queryPath).lt((Comparable<?>) value);
      case LESS_OR_EQUAL:
        return ((ComparableExpression<Comparable<?>>) queryPath).loe((Comparable<?>) value);
      case LIKE:
        if (queryPath instanceof StringExpression) {
          return ((StringExpression) queryPath).likeIgnoreCase(("%" + value) + "%");
        }

        return ((NumberExpression<?>) queryPath).like((String) value);
      case NOT_EQUAL:
        return value == null
         ? ((SimpleExpression<Object>) queryPath).isNotNull()
         : ((SimpleExpression<Object>) queryPath).ne(value);
      case EQUAL:
      default:
        if (value == null) {
          return ((SimpleExpression<Object>) queryPath).isNull();
        }
        
        if (queryPath instanceof StringExpression) {
          return ((StringExpression) queryPath).equalsIgnoreCase(((String) value));
        }
        
        if (queryPath instanceof BooleanPath) {
          return ((BooleanPath) queryPath).eq(value instanceof Boolean 
              ? (Boolean) value
              : Boolean.parseBoolean((String) value));
        }
        
        return ((SimpleExpression<Object>) queryPath).eq(value);
    }
  }


  
}
