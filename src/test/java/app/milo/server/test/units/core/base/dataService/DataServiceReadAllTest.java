package app.milo.server.test.units.core.base.dataService;

import static app.milo.server.test.units.core.setup.services.ObjectFactory.newInstance;
import static app.milo.server.test.units.core.setup.services.ObjectFactory.newTestEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import app.milo.server.core.base.GraphBuilder;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.query.QueryEntity;
import app.milo.server.core.base.dto.query.QueryExpression;
import app.milo.server.core.base.dto.query.QueryOperator;
import app.milo.server.core.context.GraphQlContextAdapter;
import app.milo.server.test.units.core.setup.entities.base.TestEntity;
import app.milo.server.test.units.core.setup.entities.base.TestPredicateBuilder;
import app.milo.server.test.units.core.setup.entities.base.TestService;
import app.milo.server.test.units.core.setup.services.RepoService;

public class DataServiceReadAllTest {
  
  private TestService service;
  
  @SuppressWarnings("unchecked")
  @BeforeEach
  public void init() throws IOException {
    service = new TestService(
        new RepoService<TestEntity>(List.of(
            newTestEntity(Map.of(
                "id", "1",
                "name", "b")),
            newTestEntity(Map.of(
                "id", "2",
                "name", "a")))),
        new TestPredicateBuilder());
    
    var mapper = new ObjectMapper();
    var context = new GraphQlContextAdapter(mapper);
    var requestMock = mock(HttpServletRequest.class);
    when(requestMock.getReader()).thenReturn(new BufferedReader(new StringReader(mapper.writeValueAsString(
        mapper.createObjectNode().put("query", "query {"
            + "  getTests {"
            + "    result {"
            + "      id"
            + "    }"
            + "  }"
            + "}")))));
    context.setRequest(requestMock);
    
    GraphBuilder<TestEntity> graph = mock(GraphBuilder.class);
    when(graph.create(any(Class.class), any(List.class))).thenReturn(null);
    
    service.setContext(context);
    service.setGraph(graph);
  }
  
  @Test
  public void readAllWithSearchParams() throws Exception {
    var test = newInstance(FilterSortPaginate.class, Map.of(
        "sort", "name",
        "size", 5,
        "page", 0,
        "search", "a"));
    
    var result = service.readAll(test);
    
    assertThat(result.getResult()).isNotEmpty();
  }
  
  @Test
  public void readAllWithExpressionParams() throws Exception {
    var test = newInstance(FilterSortPaginate.class, Map.of(
        "sort", "name",
        "size", 5,
        "page", 0,
        "expression", newInstance(QueryExpression.class, Map.of(
            "entity", newInstance(QueryEntity.class, Map.of(
                "path", "name",
                "value", "a",
                "operator", QueryOperator.EQUAL))))));
    
    var result = service.readAll(test);
    
    assertThat(result.getResult()).isNotEmpty();
  }
  
  @Test
  public void readPagedWithParams() throws Exception {
    var test = newInstance(FilterSortPaginate.class, Map.of(
        "search", "a", 
        "sort", "name", 
        "size", 5, 
        "page", 0));

    var result = service.readAll(test);

    assertThat(result.getResult()).isNotEmpty();
  }
  
  @Test
  public void readSortedWithParams() throws Exception {
    var test = newInstance(FilterSortPaginate.class, Map.of(
        "search", "a",
        "sort", "name"));
    
    var result = service.readAll(test);
    
    assertThat(result.getResult()).isNotEmpty();
  }
  
  @Test
  public void readAllEmptyParams() throws Exception {
    var test = new FilterSortPaginate();
    
    var result = service.readAll(test);
    
    assertThat(result.getResult()).isNotEmpty();
  }

}
