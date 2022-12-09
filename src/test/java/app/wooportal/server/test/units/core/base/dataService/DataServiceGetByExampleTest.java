package app.wooportal.server.test.units.core.base.dataService;

import static app.wooportal.server.test.units.core.setup.services.ObjectFactory.newTestChildEntity;
import static app.wooportal.server.test.units.core.setup.services.ObjectFactory.newTestEntity;
import static app.wooportal.server.test.units.core.setup.services.ObjectFactory.newTestListChildEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.wooportal.server.core.base.GraphBuilder;
import app.wooportal.server.core.context.GraphQlContextAdapter;
import app.wooportal.server.test.units.core.setup.entities.base.TestEntity;
import app.wooportal.server.test.units.core.setup.entities.base.TestPredicateBuilder;
import app.wooportal.server.test.units.core.setup.entities.base.TestService;
import app.wooportal.server.test.units.core.setup.services.RepoService;


public class DataServiceGetByExampleTest {
  
  private static TestService service;
  
  private OffsetDateTime created = OffsetDateTime.parse("2021-04-26T10:24:33+02:00");
  
  @SuppressWarnings("unchecked")
  @BeforeEach
  public void init() throws IOException {
    service = new TestService(
        new RepoService<TestEntity>(List.of(
            newTestEntity(Map.of(
                "id", "1",
                "child", newTestChildEntity(Map.of("name", "test")),
                "created", created
            )),
            newTestEntity(Map.of(
                "id", "2",
                "childs", List.of(newTestListChildEntity(Map.of("name", "test")))
            ))
            )), 
        new TestPredicateBuilder());
    
    var mapper = new ObjectMapper();
    var context = new GraphQlContextAdapter(mapper);
    var requestMock = mock(HttpServletRequest.class);
    when(requestMock.getReader()).thenReturn(new BufferedReader(new StringReader(mapper.writeValueAsString(
        mapper.createObjectNode().put("query", "query {"
            + "  getTest {"
            + "    id"
            + "  }"
            + "}")))));
    context.setRequest(requestMock);
    
    GraphBuilder<TestEntity> graph = mock(GraphBuilder.class);
    when(graph.create(any(Class.class), any(List.class))).thenReturn(null);
    
    service.setContext(context);
    service.setGraph(graph);
  }

  @Test
  public void getByExampleOk() throws Exception {
    var test = newTestEntity(Map.of("id", "1"));
    
    var result = service.getByExample(test);
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get().getId()).isEqualTo(test.getId());
  }
  
  @Test
  public void getByExamplWithCreatedDateOk() throws Exception {
    var test = newTestEntity(Map.of("created", created));
    
    var result = service.getByExample(test);
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get().getCreated()).isEqualTo(test.getCreated());
  }
  
  @Test
  public void getByExampleWithChild() throws Exception {
    var test = newTestEntity(Map.of(
        "id", "1",
        "child", newTestChildEntity(Map.of("name", "test"))));
    
    var result = service.getByExample(test);
    
    assertThat(result.isPresent()).isTrue();
  }
  
  @Test
  public void getByExampleWithChilds() throws Exception {
    var test = newTestEntity(Map.of(
        "childs", List.of(newTestListChildEntity(Map.of("name", "test")))));
    
    var result = service.getByExample(test);
    
    assertThat(result.isPresent()).isTrue();
  }
  
  @Test
  public void getByExampleNotFound() throws Exception {
    var test = newTestEntity(Map.of("id", "3"));
    
    var result = service.getByExample(test);
    
    assertThat(result.isPresent()).isFalse();
  }
  
  @Test
  public void getByExampleNull() throws Exception {
    var result = service.getByExample(null);
    
    assertThat(result.isPresent()).isFalse();
  }
  
}
