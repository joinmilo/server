package app.wooportal.server.core.base;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.error.exception.NotDeletableException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CrudApi<E extends BaseEntity, S extends DataService<E, ?>> {
  
  protected final static String params = "params";
  
  protected final static String entities = "entities";
  
  protected final static String entity = "entity";
  
  protected final static String id = "id";
  
  protected final static String ids = "ids";
  
  protected final S service;
  
  public CrudApi(S service) {
    this.service = service;
  }
  
  public PageableList<E> readAll(FilterSortPaginate params) {
    return service.readAll(params);
  }
  
  public Optional<E> readOne(E entity) {
    if (entity == null) {
      throw new BadParamsException("Empty entity");
    }
    
    return service.getByExampleWithContext(entity);
  }
  
  public List<E> saveAll(List<E> entities) {
    if (entities == null || entities.isEmpty()) {
      throw new BadParamsException("entities is missing", entities);
    }
    
//    // TODO: parallel streaming not possible due to null security context 
//    return entities.parallelStream().map(this::saveOne)
//        .distinct()
//        .collect(Collectors.toList());
    
//  TODO: This does not work with context! saveWithContext is looking for singleContext but we're
//  passing multi context. This should therefore get the proper context before passing it to saveOne
    return entities.stream().map(this::saveOne)
      .distinct()
      .collect(Collectors.toList());
  }
  
  public E saveOne(E entity) {
    return entity != null
        ? service.saveWithContext(entity)
        : null;
  }
  
  public Boolean deleteOne(String id) {
    return delete(id);
  }
  
  public Boolean deleteAll(List<String> ids) {
    return delete(ids.toArray(String[]::new));
  }
  
  protected Boolean delete(String... id) {
    if (id != null && id.length > 0) {
      try {
        service.deleteById(id);
      } catch(DataIntegrityViolationException e) {
        throw new NotDeletableException(e.getMessage());
      } 
    }
    return true;
  }
}
