package app.milo.server.test.units.core.setup.entities.base;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.Predicate;
import app.milo.server.core.base.dto.listing.PageableList;
import app.milo.server.core.repository.BaseRepositoryQuery;
import app.milo.server.core.repository.CollectionRepositoryQuery;
import app.milo.server.test.units.core.setup.services.RepoService;

public class TestRepositoryImpl implements TestRepository {
  
  private RepoService<TestEntity> repoService;
  
  public TestRepositoryImpl(List<TestEntity> data) {
    repoService = new RepoService<TestEntity>(data);
  }
  
  @Override
  public List<TestEntity> findAll() {
    return repoService.findAll();
  }
  
  @Override
  public PageableList<TestEntity> findAll(CollectionRepositoryQuery<TestEntity> query) {
    return repoService.findAll(query);
  }

  @Override
  public Optional<TestEntity> findOne(BaseRepositoryQuery<TestEntity> query) {
    return repoService.findOne(query);
  }

  @Override
  public <S extends TestEntity> List<S> saveAll(Iterable<S> entities) {
    return repoService.saveAll(entities);
  }
  
  @Override
  public void deleteById(String id) {
    repoService.deleteById(id);
  }

  @Override
  public <S extends TestEntity> S save(S entity) {
    return repoService.save(entity);
  }

  @Override
  public Optional<TestEntity> findById(String id) {
    return repoService.findById(id);
  }

  @Override
  public boolean existsById(String id) {
    return repoService.existsById(id);
  }

  @Override
  public Iterable<TestEntity> findAllById(Iterable<String> ids) {
    return repoService.findAllById(ids);
  }

  @Override
  public long count() {
    return repoService.count();
  }

  @Override
  public void delete(TestEntity entity) {
    repoService.delete(entity);
  }

  @Override
  public void deleteAll(Iterable<? extends TestEntity> entities) {
    repoService.deleteAll(entities);
  }
  
  @Override
  public void deleteAllById(Iterable<? extends String> ids) {
    repoService.deleteAllById(ids);
  }

  @Override
  public void deleteAll() {
    repoService.deleteAll();
  }

  @Override
  public boolean exists(Predicate predicate) {
    return repoService.exists(predicate);
  }

}
