package app.wooportal.server.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class NumberUtils {

  private static final int maxDigits = 9;

  private static final Random random = new Random();

  public static Integer generateRandomNumber(int digits) {
    if (digits > maxDigits) {
      throw new IllegalArgumentException(digits + "exceeds max digits: " + maxDigits);
    }

    var sb = new StringBuilder(digits);
    for (int i = 0; i < digits; i++) {
      sb.append((char) ('0' + random.nextInt(10)));
    }
    return Integer.valueOf(sb.toString());
  }

  public static Double round(double value, int places) {
    if (places < 0) {
      throw new IllegalArgumentException();
    }

    return BigDecimal.valueOf(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
  }
}
