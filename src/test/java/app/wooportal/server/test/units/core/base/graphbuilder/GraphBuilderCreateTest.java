package app.wooportal.server.test.units.core.base.graphbuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.persistence.AttributeNode;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.wooportal.server.core.base.GraphBuilder;
import app.wooportal.server.core.context.GraphQlContextAdapter;
import app.wooportal.server.test.units.core.setup.entities.child.TestChildEntity;

@ActiveProfiles("test")
@SpringBootTest
@WebAppConfiguration
public class GraphBuilderCreateTest {
  
  @Autowired
  private EntityManager entityManager;
  
  private GraphBuilder<TestChildEntity> graphBuilder;
  
  @BeforeEach
  public void init() {    
    graphBuilder = new GraphBuilder<TestChildEntity>();
    graphBuilder.setEntityManager(entityManager);
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void createWithContext() throws IOException {
    var mapper = new ObjectMapper();
    var context = new GraphQlContextAdapter(mapper);
    var requestMock = mock(HttpServletRequest.class);
    when(requestMock.getReader()).thenReturn(new BufferedReader(new StringReader(mapper.writeValueAsString(
        mapper.createObjectNode().put("query", "query {"
            + "  getTestChild {"
            + "    parent {"
            + "      id"
            + "      childs {"
            + "        id"
            + "      }"
            + "    }"
            + "  }"
            + "}")))));
    context.setRequest(requestMock);
    
    var result = graphBuilder.create(TestChildEntity.class, context.getSingleReadContext());
    
    assertThat(result).isNotNull();
    assertThat(result.getAttributeNodes()).allSatisfy(node -> {
      assertThat(node.getAttributeName()).isEqualTo("parent");
      assertThat(node.getSubgraphs()).anySatisfy((k, v) -> {
        assertThat(v.getAttributeNodes()).anyMatch(subNode -> 
          ((AttributeNode<?>) subNode).getAttributeName().equals("childs"));
      });
    });
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void createWithPath() throws IOException {    
    var path = "parent.childs";
    
    var result = graphBuilder.create(TestChildEntity.class, path);
    
    assertThat(result).isNotNull();
    assertThat(result.getAttributeNodes()).allSatisfy(node -> {
      assertThat(node.getAttributeName()).isEqualTo("parent");
      assertThat(node.getSubgraphs()).anySatisfy((k, v) -> {
        assertThat(v.getAttributeNodes()).anyMatch(subNode -> 
          ((AttributeNode<?>) subNode).getAttributeName().equals("childs"));
      });
    });
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void createWithPathField() throws IOException {    
    var path = "parent.childs.name";
    
    var result = graphBuilder.create(TestChildEntity.class, path);
    
    assertThat(result).isNotNull();
    assertThat(result.getAttributeNodes()).allSatisfy(node -> {
      assertThat(node.getAttributeName()).isEqualTo("parent");
      assertThat(node.getSubgraphs()).anySatisfy((k, v) -> {
        assertThat(v.getAttributeNodes()).anyMatch(subNode -> 
          ((AttributeNode<?>) subNode).getAttributeName().equals("childs"));
      });
    });
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void createWithPathSubField() throws IOException {    
    var path = "parent.childs.parent";
    
    var result = graphBuilder.create(TestChildEntity.class, path);
    
    assertThat(result).isNotNull();
    assertThat(result.getAttributeNodes()).allSatisfy(node -> {
      assertThat(node.getAttributeName()).isEqualTo("parent");
      assertThat(node.getSubgraphs()).anySatisfy((k, v) -> {
        assertThat(v.getAttributeNodes()).anyMatch(subNode -> 
          ((AttributeNode<?>) subNode).getAttributeName().equals("childs"));
      });
    });
  }
  
  @TestConfiguration
  @Profile("test")
  static class TestContextConfiguration {

    @Bean
    @Profile("test")
    public ObjectMapper mapper() {
      return new ObjectMapper();
    }

    @Bean 
    @Profile("test")
    public JavaMailSender mail() {
      return new JavaMailSenderImpl();
    }
  }

}
