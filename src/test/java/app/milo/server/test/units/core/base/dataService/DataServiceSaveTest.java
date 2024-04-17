package app.milo.server.test.units.core.base.dataService;

import static app.milo.server.test.units.core.setup.services.EntityAssertion.assertEntityMatch;
import static app.milo.server.test.units.core.setup.services.ObjectFactory.newTestChildEntity;
import static app.milo.server.test.units.core.setup.services.ObjectFactory.newTestEntity;
import static app.milo.server.test.units.core.setup.services.ObjectFactory.newTestListChildEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import app.milo.server.core.base.GraphBuilder;
import app.milo.server.core.context.GraphQlContextAdapter;
import app.milo.server.test.units.core.setup.entities.base.TestEntity;
import app.milo.server.test.units.core.setup.entities.base.TestPredicateBuilder;
import app.milo.server.test.units.core.setup.entities.base.TestService;
import app.milo.server.test.units.core.setup.entities.child.TestChildEntity;
import app.milo.server.test.units.core.setup.entities.child.TestChildPredicateBuilder;
import app.milo.server.test.units.core.setup.entities.child.TestChildService;
import app.milo.server.test.units.core.setup.entities.listChild.TestListChildEntity;
import app.milo.server.test.units.core.setup.entities.listChild.TestListChildPredicateBuilder;
import app.milo.server.test.units.core.setup.entities.listChild.TestListChildService;
import app.milo.server.test.units.core.setup.services.RepoService;

public class DataServiceSaveTest {
  
  private TestService service;
  
  private TestChildService childService;
  
  private TestListChildService listChildService;
  
  private GraphQlContextAdapter contextAdapter; 
  
  private ObjectMapper mapper = new ObjectMapper();
  
  @SuppressWarnings("unchecked")
  @BeforeEach
  public void init() throws Exception {
    
    var child1 = newTestChildEntity(Map.of(
        "id", "1", 
        "name", "child1"));
    
    var listChild1 = newTestListChildEntity(Map.of(
        "id", "1", 
        "name", "listChild1"));
    var listChild2 = newTestListChildEntity(Map.of(
        "id", "2",
        "name", "listChild2"));
    var listChild3 = newTestListChildEntity(Map.of(
        "id", "3",
        "name", "listChild3"));
    
    var entity1 = newTestEntity(Map.of(
        "id", "1",
        "name", "test1",
        "child", child1));
    var entity2 = newTestEntity(Map.of(
        "id", "2",
        "name", "test2",
        "childs", List.of(listChild1, listChild2, listChild3)));
    
    contextAdapter = new GraphQlContextAdapter(mapper);
    
    GraphBuilder<TestChildEntity> childGraph = mock(GraphBuilder.class);
    when(childGraph.create(any(Class.class), any(List.class))).thenReturn(null);
    childService = new TestChildService(
        new RepoService<TestChildEntity>(List.of(child1)), 
        new TestChildPredicateBuilder());
    childService.setContext(contextAdapter);
    childService.setObjectMapper(mapper);
    childService.setGraph(childGraph);
    
    GraphBuilder<TestListChildEntity> listChildGraph = mock(GraphBuilder.class);
    when(listChildGraph.create(any(Class.class), any(List.class))).thenReturn(null);
    listChildService = new TestListChildService(
        new RepoService<TestListChildEntity>(List.of(listChild1, listChild2, listChild3)), 
        new TestListChildPredicateBuilder());
    listChildService.setContext(contextAdapter);
    listChildService.setObjectMapper(mapper);
    listChildService.setGraph(listChildGraph);
    
    GraphBuilder<TestEntity> graph = mock(GraphBuilder.class);
    when(graph.create(any(Class.class), any(List.class))).thenReturn(null);
    service = new TestService(
        new RepoService<TestEntity>(List.of(entity1, entity2)), 
        new TestPredicateBuilder(), 
        childService,
        listChildService);
    service.setContext(contextAdapter);
    service.setObjectMapper(mapper);
    service.setGraph(graph);
  }
  
  @Test
  public void saveNewWithoutContext() throws Exception {
    var test = newTestEntity(Map.of(
        "id", "4",
        "name", "newEntity"));
    var sizeBefore = service.getRepo().findAll().size();
    
    var result = service.save(test);
    
    assertEntityMatch(test, result);
    assertThat(sizeBefore).isLessThan(service.getRepo().findAll().size());
  }
  
  @Test
  public void saveExistingWithoutContext() throws Exception {
    var test = newTestEntity(Map.of(
        "id", "1",
        "name", "newEntity"));
    var sizeBefore = service.getRepo().findAll().size();
    
    var result = service.save(test);
    
    assertEntityMatch(test, result);
    assertThat(sizeBefore).isEqualTo(service.getRepo().findAll().size());
  }
  
  @Test
  public void saveChildWithoutContext() throws Exception {
    var testChild = newTestChildEntity(Map.of(
        "id", "2", 
        "name", "child2"));
    var test = newTestEntity(Map.of(
        "id", "1", 
        "name", "test1",
        "child", testChild));
    testChild.setParent(test);
    
    var sizeParentBefore = service.getRepo().findAll().size();
    var sizeChildBefore = childService.getRepo().findAll().size();
    
    var result = service.save(test);
    
    assertEntityMatch(test, result);
    assertThat(sizeParentBefore).isEqualTo(service.getRepo().findAll().size());
    assertThat(sizeChildBefore).isLessThan(childService.getRepo().findAll().size());
  }
  
  @Test
  public void saveChildListWithoutContext() throws Exception {
    var test = newTestEntity(Map.of(
        "id", "2", 
        "name", "newNameTest2",
        "childs", List.of(
            newTestListChildEntity(Map.of(
              "name", "listChild1")),
            newTestListChildEntity(Map.of(
                "id", "2",
                "name", "newListChildName2")),
            newTestListChildEntity(Map.of(
                "name", "newListChild"))),
        "oneToOneChild", newTestChildEntity(Map.of(
            "id", "1", 
            "name", "child1"))));
    var sizeParentBefore = service.getRepo().findAll().size();
    var sizeListChildBefore = listChildService.getRepo().findAll().size();
    
    var result = service.save(test);
    
    assertEntityMatch(test, result);
    assertThat(sizeParentBefore).isEqualTo(service.getRepo().findAll().size());
    assertThat(sizeListChildBefore).isEqualTo(listChildService.getRepo().findAll().size());
  }
  
  @Test
  public void saveAndAddContext() throws Exception {
    var test = newTestEntity(Map.of(
        "id", "2", 
        "name", "newNameTest2",
        "removeContextField", "test",
        "childs", List.of(
            newTestListChildEntity(Map.of(
              "name", "listChild1")),
            newTestListChildEntity(Map.of(
                "id", "2",
                "name", "newListChildName2")),
            newTestListChildEntity(Map.of(
                "name", "newListChild")))));
    var context = ((ObjectNode) mapper.valueToTree(test));
    
    var sizeParentBefore = service.getRepo().findAll().size();
    var sizeListChildBefore = listChildService.getRepo().findAll().size();
    
    var result = service.save(test, context);
    
    assertEntityMatch(test, result, context);
    assertThat(sizeParentBefore).isEqualTo(service.getRepo().findAll().size());
    assertThat(sizeListChildBefore).isEqualTo(listChildService.getRepo().findAll().size());
  }
  
  @Test
  public void saveWithContext() throws Exception {
    var test = newTestEntity(Map.of(
        "id", "2", 
        "name", "newNameTest2",
        "childs", List.of(
            newTestListChildEntity(Map.of(
              "name", "listChild1")),
            newTestListChildEntity(Map.of(
                "id", "2",
                "name", "newListChildName2")),
            newTestListChildEntity(Map.of(
                "name", "newListChild")))));
    var requestMock = mock(HttpServletRequest.class);
    when(requestMock.getReader()).thenReturn(new BufferedReader(new StringReader(mapper.writeValueAsString(
        mapper.createObjectNode().set(
            "variables", 
            mapper.createObjectNode().set("item", mapper.valueToTree(test)))))));
    contextAdapter.setRequest(requestMock);
    
    var sizeParentBefore = service.getRepo().findAll().size();
    var sizeListChildBefore = listChildService.getRepo().findAll().size();
    
    var result = service.saveWithContext(test);
    
    assertEntityMatch(test, result, contextAdapter.getSingleSaveContext());
    assertThat(sizeParentBefore).isEqualTo(service.getRepo().findAll().size());
    assertThat(sizeListChildBefore).isEqualTo(listChildService.getRepo().findAll().size());
  }
  
  @Test
  public void saveWithContextNull() throws Exception {    
    var result = service.saveWithContext(null);
    
    assertThat(result).isNull();
  }
  
  @Test
  public void saveAll() throws Exception {
    var test = List.of(
        newTestEntity(Map.of(
          "id", "2", 
          "name", "newNameTest2",
          "childs", List.of(
              newTestListChildEntity(Map.of(
                "name", "listChild1")),
              newTestListChildEntity(Map.of(
                  "id", "2",
                  "name", "newListChildName2")),
              newTestListChildEntity(Map.of(
                  "name", "newListChild"))))),
          newTestEntity(Map.of(
              "id", "1", 
              "name", "newNameTest1"))
        ); 
    var sizeParentBefore = service.getRepo().findAll().size();
    var sizeListChildBefore = listChildService.getRepo().findAll().size();
    
    var result = service.saveAll(test);
    
    assertThat(result).isNotEmpty();
    assertThat(sizeParentBefore).isEqualTo(service.getRepo().findAll().size());
    assertThat(sizeListChildBefore).isEqualTo(listChildService.getRepo().findAll().size());
  }

}
