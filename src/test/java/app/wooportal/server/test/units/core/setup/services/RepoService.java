package app.wooportal.server.test.units.core.setup.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.querydsl.core.types.Predicate;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.repository.BaseRepositoryQuery;
import app.wooportal.server.core.repository.CollectionRepositoryQuery;
import app.wooportal.server.core.repository.DataRepository;

public class RepoService<E extends BaseEntity> implements DataRepository<E> {

  private List<E> data;
  
  public RepoService(List<E> data) {
    this.data = new ArrayList<>();
    this.data.addAll(data);
  }

  public List<E> findAll() {
    return data;
  }
  
  public PageableList<E> findAll(CollectionRepositoryQuery<E> query) {
    PageableList<E> result = query.getPredicate() != null
        ? new PageableList<E>(find(query.getPredicate()))
        : new PageableList<E>(data);
    
    if (query.getLimit() != null) {
      result.setResult(result.getList().subList(0, query.getLimit()));
    }
    
    return result;
  }

  public Optional<E> findOne(BaseRepositoryQuery<E> query) {
    var result = find(query.getPredicate());
    
    return Optional.ofNullable(result != null && !result.isEmpty()
        ? result.get(0)
        : null);
  }
  
  private List<E> find(Predicate predicate) {
    return data.stream().filter(entity -> {
      try {
        QueryDslAssertion.assertPredicate(predicate, entity);
        return true;
      } catch (Throwable t) {
        return false;
      }
    }).collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  public <S extends E> List<S> saveAll(Iterable<S> entities) {
    entities.forEach(e -> {
      save(e);
    });
    
    return (List<S>) data;
  }

  public void deleteById(String id) {
    data.removeIf(e -> e.getId().equals(id));
  }

  public <S extends E> S save(S entity) {
    if (data == null) {
      data = new ArrayList<>();
    }
    
    if (!data.contains(entity)) {
      data.add(entity);
    } else {
      data.remove(entity);
      data.add(entity);
    }
    return entity;
  }

  public Optional<E> findById(String id) {
    for (var element : data) {
      if (element.getId().equals(id)) {
        return Optional.of(element);
      }
    }
    return Optional.empty();
  }

  public boolean existsById(String id) {
    return findById(id).isPresent();
  }

  public Iterable<E> findAllById(Iterable<String> ids) {
    var result = data.stream().filter(element -> {
      for (var id : ids) {
        if (element.getId().equals(id)) {
          return true;
        }
      }
      return false;
    }).collect(Collectors.toList());
    
    return (Iterable<E>) result;
  }

  public long count() {
    return data != null ? data.size() : 0;
  }

  public void delete(E entity) {
    data.remove(entity);
  }

  public void deleteAll(Iterable<? extends E> entities) {
    if (entities != null) {
      for (var entity : entities) {
        data.remove(entity);
      }
    }
  }
  
  public void deleteAllById(Iterable<? extends String> ids) {
    data.removeIf(element -> {
      for (var id : ids) {
        if (element.getId().equals(id)) {
          return true;
        }
      }
      return false;
    });
  }

  public void deleteAll() {
    data = new ArrayList<>();
  }

  public boolean exists(Predicate predicate) {
    return !find(predicate).isEmpty();
  }
  
}
