package app.wooportal.server.test.units.core.setup.entities.listChild;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.Predicate;

import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.repository.BaseRepositoryQuery;
import app.wooportal.server.core.repository.CollectionRepositoryQuery;
import app.wooportal.server.test.units.core.setup.services.RepoService;

public class TestListChildRepositoryImpl implements TestListChildRepository {
  
  private RepoService<TestListChildEntity> repoService;
  
  public TestListChildRepositoryImpl(List<TestListChildEntity> data) {
    repoService = new RepoService<TestListChildEntity>(data);
  }
  
  @Override
  public List<TestListChildEntity> findAll() {
    return repoService.findAll();
  }
  
  @Override
  public PageableList<TestListChildEntity> findAll(CollectionRepositoryQuery<TestListChildEntity> query) {
    return repoService.findAll(query);
  }
  
  @Override
  public Optional<TestListChildEntity> findOne(BaseRepositoryQuery<TestListChildEntity> query) {
    return repoService.findOne(query);
  }

  @Override
  public <S extends TestListChildEntity> List<S> saveAll(Iterable<S> entities) {
    return repoService.saveAll(entities);
  }
  
  @Override
  public void deleteById(String id) {
    repoService.deleteById(id);
  }

  @Override
  public <S extends TestListChildEntity> S save(S entity) {
    return repoService.save(entity);
  }

  @Override
  public Optional<TestListChildEntity> findById(String id) {
    return repoService.findById(id);
  }

  @Override
  public boolean existsById(String id) {
    return repoService.existsById(id);
  }

  @Override
  public Iterable<TestListChildEntity> findAllById(Iterable<String> ids) {
    return repoService.findAllById(ids);
  }

  @Override
  public long count() {
    return repoService.count();
  }

  @Override
  public void delete(TestListChildEntity entity) {
    repoService.delete(entity);
  }

  @Override
  public void deleteAll(Iterable<? extends TestListChildEntity> entities) {
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
