package app.wooportal.server.core.context;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.language.Field;
import graphql.language.SelectionSetContainer;
import graphql.parser.Parser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class GraphQlContextAdapter implements ApiContextAdapter {
  
  protected ObjectMapper objectMapper;
  
  @Autowired
  private HttpServletRequest request;
  
  public GraphQlContextAdapter(
      ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }
  
  @Override
  public JsonNode getSingleSaveContext() {
    return getSaveContext();
  }
  
  @Override
  public JsonNode getMultiSaveContext() {
    return getSaveContext();
  }
  
  public List<Field> getSingleReadContext() {
    var context = getReadContext();
    if (context != null && !context.isEmpty()) {
      return context.get(0).getSelectionSet().getSelectionsOfType(Field.class);
    }
    return null;
  }
  
  public List<Field> getMultiReadContext() {
    for (Field selection : getSingleReadContext()) {
      if (selection.getName().equalsIgnoreCase("result")) {
        return selection.getSelectionSet().getSelectionsOfType(Field.class); 
      }
    }
    return null;
  }
  
  public List<Field> getReadContext() {
    try {
      var parser = new Parser();
      var document = parser.parseDocument(getContext("query").asText());
      for (var def : document.getDefinitions()) {
        if (def instanceof SelectionSetContainer) {
          return ((SelectionSetContainer<?>) def).getSelectionSet().getSelectionsOfType(Field.class);
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  private JsonNode getSaveContext() {
    try {
      return getContext("variables").fields().next().getValue(); 
    } catch (Exception ignored) { }
    return null;
  }
  
  private JsonNode getContext(String baseKey) throws IOException {
    return objectMapper.readTree(payload()).get(baseKey);
  }

  private String payload() throws IOException {
    return request != null
        ? request.getReader().lines().collect(Collectors.joining(System.lineSeparator()))
        : "";
  }
}
