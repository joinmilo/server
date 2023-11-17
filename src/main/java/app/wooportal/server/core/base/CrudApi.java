package app.wooportal.server.core.base;

import java.util.List;
import java.util.Optional;
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

    return service.saveAllWithContext(entities);
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
