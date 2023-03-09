package app.wooportal.server.core.context;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.language.Definition;
import graphql.language.Field;
import graphql.language.FragmentDefinition;
import graphql.language.SelectionSetContainer;
import graphql.parser.InvalidSyntaxException;
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
    var definitions = getReadContext();
    if (definitions == null) {
      return null;
    }
    
    List<Field> selections = null;
    for (var def : definitions) {
      if (def instanceof FragmentDefinition) {
        return fragmentContext(def);
      } else if (def instanceof SelectionSetContainer) {
        selections = ((SelectionSetContainer<?>) def).getSelectionSet().getSelectionsOfType(Field.class);
      }
    }
    return selectionFragment(selections);
  }

  public List<Field> getMultiReadContext() {
    for (var selection : getSingleReadContext()) {
      if (selection.getName().equalsIgnoreCase("result")) {
        return selection.getSelectionSet().getSelectionsOfType(Field.class);
      }
    }
    return null;
  }
  
  @SuppressWarnings("rawtypes")
  private List<Definition> getReadContext() {
    try {
      return new Parser().parseDocument(getContext("query").asText()).getDefinitions();
    } catch (InvalidSyntaxException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  private List<Field> fragmentContext(Definition<?> def) {
    return ((FragmentDefinition) def).getSelectionSet().getSelectionsOfType(Field.class);
  }
  
  private List<Field> selectionFragment(List<Field> selections) {
    if (selections != null && !selections.isEmpty()) {
      return selections.get(0).getSelectionSet().getSelectionsOfType(Field.class);
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
