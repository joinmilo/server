package app.wooportal.server.core.context;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraphQLContext {
  
  private Map<String, GraphQLContext> subFields;
  
  private String field;

}
