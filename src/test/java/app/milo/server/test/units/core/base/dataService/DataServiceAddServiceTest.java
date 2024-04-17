package app.milo.server.test.units.core.base.dataService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;
import app.milo.server.test.units.core.setup.entities.base.TestService;
import app.milo.server.test.units.core.setup.entities.child.TestChildService;
import app.milo.server.test.units.core.setup.entities.listChild.TestListChildService;

public class DataServiceAddServiceTest {
    
  @Test
  public void addServiceOk() throws Exception {
    var test = new TestService(
        null, null, new TestChildService(null, null), new TestListChildService(null, null));
    
    assertThat(test).isNotNull();
  }
  
  @Test
  public void addServiceDuplicate() throws Exception {
    var test = new TestService(
        null, null, new TestChildService(null, null), new TestListChildService(null, null));
    
    var result = catchThrowable(() -> test.setService("child", new TestChildService(null, null)));
    
    assertThat(result).isInstanceOfAny(IllegalArgumentException.class);
  }
  
  @Test
  public void addFieldNotExists() throws Exception {
    var test = new TestService(
        null, null, new TestChildService(null, null), new TestListChildService(null, null));
    
    var result = catchThrowable(() -> test.setService("fieldNotExists", new TestChildService(null, null)));
    
    assertThat(result).isInstanceOfAny(IllegalArgumentException.class);
  }
}
