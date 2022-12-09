package app.wooportal.server.core.base.dto.listing;

import app.wooportal.server.core.base.dto.query.QueryExpression;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilterSortPaginate {
  
  private QueryExpression expression;
  private String search; 
  
  private Integer page; 
  private Integer size; 
  private String sort; 
  private String dir;
  
  public String getSearch() {
    return search != null && !search.isEmpty()
        ? "%" + search + "%"
        : null;
  }
  
}
