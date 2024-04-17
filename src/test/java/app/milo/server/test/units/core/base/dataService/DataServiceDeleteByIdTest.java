package app.milo.server.test.units.core.base.dataService;

import static app.milo.server.test.units.core.setup.services.ObjectFactory.newTestEntity;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.milo.server.test.units.core.setup.entities.base.TestEntity;
import app.milo.server.test.units.core.setup.entities.base.TestService;
import app.milo.server.test.units.core.setup.services.RepoService;

public class DataServiceDeleteByIdTest {
  
  private TestService service;
  
  private RepoService<TestEntity> testRepo;
  
  private List<TestEntity> data = List.of(
      newTestEntity(Map.of("id", "1")),
      newTestEntity(Map.of("id", "2"))
  );
  
  @BeforeEach
  public void init() {
    testRepo = new RepoService<>(data);
    service = new TestService(testRepo, null);
  }
  
  @Test
  public void deleteByIdOk() throws Exception {
    var test = "1";
    
    service.deleteById(test);
    
    assertThat(testRepo.findAll()).noneMatch(e -> e.getId().equals(test));
  }
  
  @Test
  public void deleteByIdMultipleOk() throws Exception {
    String[] test = { "1", "2" };
    
    service.deleteById(test);
    
    assertThat(testRepo.findAll()).noneMatch(e -> List.of(test).contains(e.getId()));
  }
  
  @Test
  public void deleteByIdEmptyIds() throws Exception {
    String[] test = { "", "" };
    
    service.deleteById(test);
    
    assertThat(testRepo.findAll()).containsAll(data);
  }
  
  @Test
  public void deleteByIdNull() throws Exception {
    service.deleteById();
    
    assertThat(testRepo.findAll()).containsAll(data);
  }
  
  @Test
  public void deleteAllNullIds() throws Exception {
    String[] test = { null, null };
    
    service.deleteById(test);
    
    assertThat(testRepo.findAll()).containsAll(data);
  }
}
