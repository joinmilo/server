package app.milo.server.core.repository;

import jakarta.persistence.EntityGraph;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.base.GraphBuilder;
import app.milo.server.core.utils.SortPageUtils;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CollectionRepositoryQuery<E extends BaseEntity> extends BaseRepositoryQuery<E> {
  
  private Integer limit;
  
  private PageRequest page;
  
  private Sort sort;
  
  public static <E extends BaseEntity> CollectionRepositoryQuery<E> init(
      Class<E> entityClass,
      GraphBuilder<E> graphBuilder) {
    return init(entityClass, graphBuilder, false);
  }

  public static <E extends BaseEntity> CollectionRepositoryQuery<E> init(
      Class<E> entityClass,
      GraphBuilder<E> graphBuilder,
      boolean defaultSort) {
    var instance = new CollectionRepositoryQuery<E>();
    var builder = new BooleanBuilder();
    
    instance
      .setEntityClass(entityClass)
      .setGraphBuilder(graphBuilder)
      .setPredicateBuilder(builder);
    
    if (defaultSort) {
      var defaultSortProp = SortPageUtils.createDefaultSort(entityClass);
      if (defaultSortProp.isPresent()) {
        instance.setSort(defaultSortProp.get());
      }
    }
    return instance;
  }

  @Override
  public CollectionRepositoryQuery<E> addGraph(EntityGraph<E> graph) {
    super.addGraph(graph);
    return this;
  }
  
  @Override
  public CollectionRepositoryQuery<E> and(Predicate predicate) {
    super.and(predicate);
    return this;
  }
  
  @Override
  public CollectionRepositoryQuery<E> or(Predicate predicate) {
    super.or(predicate);
    return this;
  }
  
  public CollectionRepositoryQuery<E> addGraph(Sort sort) {
    return addGraph(graphBuilder.create(
        entityClass, 
        sort.get().map(order -> order.getProperty()).toArray(String[]::new))); 
  }
  
  public CollectionRepositoryQuery<E> setSort(Sort sort) {
    this.sort = sort;
    
    if (this.page != null) {
      this.page = PageRequest.of(
          this.page.getPageNumber(),
          this.page.getPageSize(),
          this.sort);
    }
    
    addGraph(sort);
    return this;
  }
  
  public CollectionRepositoryQuery<E> setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }
  
  public CollectionRepositoryQuery<E> setPage(PageRequest page) {
    this.page = page;
    
    if (this.sort != null 
        && (page.getSort() == null || page.getSort().isUnsorted())) {
      this.page = PageRequest.of(
          this.page.getPageNumber(),
          this.page.getPageSize(),
          this.sort);
    }
    
    return this;
  }
  
}
