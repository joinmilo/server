package app.milo.server.test.units.core.base.dataService;

import static app.milo.server.test.units.core.setup.services.ObjectFactory.newTestEntity;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.milo.server.test.units.core.setup.entities.base.TestEntity;
import app.milo.server.test.units.core.setup.entities.base.TestService;
import app.milo.server.test.units.core.setup.services.RepoService;


public class DataServiceDeleteAllTest {
  
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
  public void deleteAllOk() throws Exception {
    var test = List.of(
        newTestEntity(Map.of("id", "1")),
        newTestEntity(Map.of("id", "2"))
    );
    
    service.deleteAll(test);
    
    assertThat(testRepo.findAll()).noneMatch(e -> test.contains(e));
  }
  
  @Test
  public void deleteAllEmptyList() throws Exception {
    var test = new ArrayList<TestEntity>();
    
    service.deleteAll(test);
    
    assertThat(testRepo.findAll()).containsAll(data);
  }
  
  @Test
  public void deleteAllNullEntities() throws Exception {
    var test = new ArrayList<TestEntity>();
    test.add(null);
    
    service.deleteAll(test);
    
    assertThat(testRepo.findAll()).containsAll(data);
  }
  
  @Test
  public void deleteAllEmptyIds() throws Exception {
    var test = List.of(
        newTestEntity(Map.of("id", "")),
        newTestEntity(Map.of("id", ""))
    );
    
    service.deleteAll(test);
    
    assertThat(testRepo.findAll()).containsAll(data);
  }
  
  @Test
  public void deleteAllNullIds() throws Exception {
    var test = List.of(
        newTestEntity(null),
        newTestEntity(null)
    );
    
    service.deleteAll(test);
    
    assertThat(testRepo.findAll()).containsAll(data);
  }

}
