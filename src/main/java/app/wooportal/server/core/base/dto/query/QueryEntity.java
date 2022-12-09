package app.wooportal.server.core.base.dto.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueryEntity {

  private String path;
  
  private String value;
  
  private QueryOperator operator;
  
  public QueryOperator getOperator() {
    return operator == null
        ? QueryOperator.EQUAL
        : operator;
  }
}
