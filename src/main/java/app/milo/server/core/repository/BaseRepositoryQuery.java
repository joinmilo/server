package app.milo.server.core.repository;

import jakarta.persistence.EntityGraph;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.base.GraphBuilder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BaseRepositoryQuery<E extends BaseEntity> {
  
  protected Class<E> entityClass;
  
  protected EntityGraph<E> graph;
  
  protected GraphBuilder<E> graphBuilder;
  
  protected BooleanBuilder predicateBuilder;
  
  protected BaseRepositoryQuery() { }
  
  public static <E extends BaseEntity> BaseRepositoryQuery<E> init(
      Class<E> entityClass,
      GraphBuilder<E> graphBuilder) {
    var instance = new BaseRepositoryQuery<E>();
    var builder = new BooleanBuilder();
    
    instance
      .setEntityClass(entityClass)
      .setGraphBuilder(graphBuilder)
      .setPredicateBuilder(builder);
    
    return instance;
  }

  public BaseRepositoryQuery<E> setEntityClass(Class<E> entityClass) {
    this.entityClass = entityClass;
    return this;
  }
  
  public BaseRepositoryQuery<E> setGraphBuilder(GraphBuilder<E> graphBuilder) {
    this.graphBuilder = graphBuilder;
    return this;
  }
  
  public BaseRepositoryQuery<E> setPredicateBuilder(BooleanBuilder builder) {
    this.predicateBuilder = builder;
    return this;
  }

  public BaseRepositoryQuery<E> addGraph(EntityGraph<E> graph) {
    if (graph != null) {
      this.graph = this.graph == null
          ? graph
          : graphBuilder.merge(entityClass, this.graph, graph);
    }
    return this;
  }
  
  public BooleanBuilder getPredicate() {
    return this.predicateBuilder;
  }
  
  public BaseRepositoryQuery<E> and(Predicate predicate) {
    this.predicateBuilder.and(predicate);
    return this;
  }
  
  public BaseRepositoryQuery<E> or(Predicate predicate) {
    this.predicateBuilder.or(predicate);
    return this;
  }
  
}
