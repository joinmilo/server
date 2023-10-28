package app.wooportal.server.test.units.core.utils.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

import app.wooportal.server.core.utils.NumberUtils;

public class NumberUtilsTest {

  @Test
  public void generateRandomValid() throws Exception {
    var digits = 5;

    var result = NumberUtils.generateRandomNumber(digits);

    var chars = ("" + result).toCharArray();
    assertThat(chars.length).isLessThanOrEqualTo(digits);
  }

  @Test
  public void generateRandomValidInvalid() throws Exception {
    var digits = 10;
    
    var result = catchThrowable(() -> NumberUtils.generateRandomNumber(digits));

    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
  
  @Test
  public void roundDownValid() throws Exception {
    var test = 10.11111;

    var result = NumberUtils.round(test, 2);
    
    assertThat(result).isEqualTo(10.11);
  }
  
  @Test
  public void roundDownZeroPlacesValid() throws Exception {
    var test = 10.11111;

    var result = NumberUtils.round(test, 0);
    
    assertThat(result).isEqualTo(10);
  }
  
  @Test
  public void roundUpValid() throws Exception {
    var test = 10.5555;

    var result = NumberUtils.round(test, 2);
    
    assertThat(result).isEqualTo(10.56);
  }
  
  @Test
  public void roundInvalid() throws Exception {
    var test = 10.5555;
    
    var result = catchThrowable(() -> NumberUtils.round(test, -2));

    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
