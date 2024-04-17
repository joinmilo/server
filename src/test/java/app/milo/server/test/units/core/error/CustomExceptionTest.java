package app.milo.server.test.units.core.error;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import app.milo.server.core.error.exception.BadParamsException;
import app.milo.server.core.error.exception.InvalidTokenException;
import app.milo.server.core.error.exception.NotFoundException;

public class CustomExceptionTest {
  
  @Test
  public void badParamsTest() {
    var message = "test";
    String[] params = {"1", "2", "3"};
    
    var result = new BadParamsException(message, "1", "2", "3");
    
    assertThat(result.getMessage()).containsSubsequence(message);
    assertThat(result.getMessage()).containsSubsequence(params);
  }
  
  @Test
  public void notFoundTest() {
    var message = "test";
    String[] params = {"1", "2", "3"};
    
    var result = new NotFoundException(message, "1", "2", "3");
    
    assertThat(result.getMessage()).containsSubsequence(message);
    assertThat(result.getMessage()).containsSubsequence(params);
  }
  
  @Test
  public void invalidTokenTest() {
    var message = "test";
    String[] params = {"1", "2", "3"};
    
    var result = new InvalidTokenException(message, "1", "2", "3");
    
    assertThat(result.getMessage()).containsSubsequence(message);
    assertThat(result.getMessage()).containsSubsequence(params);
  }

}
