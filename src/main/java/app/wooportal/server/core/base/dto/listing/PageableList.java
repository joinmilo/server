package app.wooportal.server.core.base.dto.listing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;

import io.leangen.graphql.annotations.GraphQLIgnore;
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
public class PageableList<E> {

  private Iterable<E> result = new ArrayList<E>();
  
  private long total;
  
  public PageableList(Iterable<E> result) {
    setResult(result);
  }
  
  public void setResult(Iterable<E> result) {
    if (result != null) {
      this.result = result;
      if (result instanceof Collection<?>) {
        total = ((Collection<?>) result).size();
      }
      
      if (result instanceof Page<?>) {
        total = ((Page<?>) result).getTotalElements();
      } 
    }
  }
  
  @GraphQLIgnore
  public List<E> getList() {
    if (!isEmpty()) {
      if (result instanceof Collection<?>) {
        return new ArrayList<>((Collection<? extends E>) result);
      }
      
      if (result instanceof Page<?>) {
        return ((Page<E>) result).toList();
      }
      
      throw new UnsupportedOperationException(result.getClass().getName() + " is not a known type");
    }
    return Collections.emptyList();
  }

  @GraphQLIgnore
  public boolean isEmpty() {
    return result == null
        ? true
        : result.iterator() != null && !result.iterator().hasNext();
  }

  public E get(int idx) {   
    if (result != null && result.iterator() != null) {
      var currentIdx = 0;
      var iterator = result.iterator();
      while (iterator.hasNext()) {
        var current = iterator.next();
        if (currentIdx == idx) {
          return current;
        }
        currentIdx++;
      }
    }
    return null;
  }

}
