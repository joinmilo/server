package app.milo.server.core.base.dto.query;

import java.util.List;

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
public class QueryConjunction {
  
  private ConjunctionOperator operator;
  
  private List<QueryExpression> operands;
  
  public ConjunctionOperator getOperator() {
    return operator == null
        ? ConjunctionOperator.AND
        : operator;
  }

}
