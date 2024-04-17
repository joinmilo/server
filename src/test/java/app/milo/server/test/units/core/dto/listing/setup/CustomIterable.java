package app.milo.server.test.units.core.dto.listing.setup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomIterable<E> implements Iterable<E> {
  
  private List<E> result;
  
  public CustomIterable() {
    this.result = new ArrayList<E>();
  }
  
  public CustomIterable(List<E> result) {
    this.result = result;
  }

  @Override
  public Iterator<E> iterator() {
    return result.iterator();
  }

}
