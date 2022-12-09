package app.wooportal.server.core.repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import org.hibernate.annotations.QueryHints;
import org.hibernate.graph.GraphSemantic;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.NonUniqueResultException;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.AbstractJPAQuery;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.utils.ReflectionUtils;

@Transactional
public class QueryableReadRepositoryImpl<T extends BaseEntity> extends QuerydslJpaPredicateExecutor<T>
    implements QueryableReadRepository<T> {

  private static final EntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;

  private final EntityPath<T> path;
  private final PathBuilder<T> builder;
  private final Querydsl querydsl;

  public QueryableReadRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
      EntityManager entityManager) {
    super(entityInformation, entityManager, resolver, null);
    this.path = resolver.createPath(entityInformation.getJavaType());
    this.builder = new PathBuilder<T>(path.getType(), path.getMetadata());
    this.querydsl = new Querydsl(entityManager, builder);
  }
  
  @Override
  public Optional<T> findOne(BaseRepositoryQuery<T> repoQuery) {
    try {
      
      if (repoQuery.getPredicate().getValue() == null) {
        return Optional.empty();
      }
      
      var query = createQuery(repoQuery.getPredicate()).select(path);
      if (repoQuery.getGraph() != null) {
        ((AbstractJPAQuery<?, ?>) query).setHint(GraphSemantic.FETCH.getJpaHintName(),
            repoQuery.getGraph());
      }
      return Optional.ofNullable(query.fetchOne());
    } catch (NonUniqueResultException ex) {
      throw new IncorrectResultSizeDataAccessException(ex.getMessage(), 1, ex);
    }
  }
  
  @Override
  public PageableList<T> findAll(CollectionRepositoryQuery<T> query) {
    return query.getPage() != null
        ? new PageableList<T>(findAll(query.getPredicate(), query.getPage(), query.getGraph()))
        : new PageableList<T>(findAll(query.getPredicate(), query.getSort(), query.getLimit(), query.getGraph()));
  }
  
  public Page<T> findAll(Predicate predicate, Pageable pageable, EntityGraph<T> graph) {
    var idPath = ReflectionUtils.get("id", path).map(value -> ((StringPath) value));
    
    if (idPath.isEmpty()) {
      throw new RuntimeException(path.getType().getName() + " has no id property");
    }
    
    final JPQLQuery<?> countQuery = createCountQuery(predicate).distinct();
    JPQLQuery<T> query = querydsl.applySorting(pageable.getSort(), createQuery(
        idPath.get().in(fetchIds(predicate, pageable, idPath.get()))).select(path));
    
    ((AbstractJPAQuery<?, ?>) query).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
    ((AbstractJPAQuery<?, ?>) countQuery).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
    
    if (graph != null) {
      ((AbstractJPAQuery<?, ?>) query).setHint(GraphSemantic.FETCH.getJpaHintName(), graph); 
    }
    
    return PageableExecutionUtils.getPage(
        query.distinct().fetch(), 
        pageable, 
        countQuery::fetchCount);
  }
  
  private List<String> fetchIds(
      Predicate predicate, 
      Pageable pageable,
      StringPath id) {
    var idQuery = createQuery(predicate).select(id).distinct();
    ((AbstractJPAQuery<?, ?>) idQuery).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
    return querydsl.applyPagination(pageable, idQuery).fetch();
  }

  @SuppressWarnings("unchecked")
  public List<T> findAll(Predicate predicate, Sort sort, Integer limit, EntityGraph<T> graph) {
    JPQLQuery<T> query = createQuery(predicate)
        .select(path);
    
    if (limit != null && limit > 0) {
      if (graph != null) {
        // using join fetch with limit will lead to in memory limiting in Hibernate (HHH000104).
        // Therefore two queries will be executed:
        //      1. Get ids only with predicate
        //      2. Create query with ids in result set
        var idPath = ReflectionUtils.get("id", path).map(value -> ((StringPath) value));
        if (idPath.isEmpty()) {
          throw new RuntimeException(path.getType().getName() + " has no id property");
        }
        
        var idQuery = createQuery(predicate)
            .select(idPath.get())
            .distinct()
            .limit(limit);
        
        ((AbstractJPAQuery<?, ?>) idQuery).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
        query = (JPQLQuery<T>) createQuery(idPath.get().in(idQuery));
      } else {
        query.limit(limit);
      }
    }
    
    ((AbstractJPAQuery<?, ?>) query).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
    if (graph != null) {
      ((AbstractJPAQuery<?, ?>) query).setHint(GraphSemantic.FETCH.getJpaHintName(), graph);
    }
    
    return sort != null && sort.isSorted()
        ? querydsl.applySorting(sort, query).distinct().fetch()
        : query.fetch();
  }
  
}
