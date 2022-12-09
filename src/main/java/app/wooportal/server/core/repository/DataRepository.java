package app.wooportal.server.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import app.wooportal.server.core.base.BaseEntity;

@NoRepositoryBean
public interface DataRepository<T extends BaseEntity>
    extends CrudRepository<T, String>, QueryableReadRepository<T> {
  
  @Override
  List<T> findAll();
  
  @Override
  <S extends T> List<S> saveAll(Iterable<S> entities);
}
