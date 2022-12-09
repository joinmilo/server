package app.wooportal.server.core.utils;

import java.util.Random;

public class StringUtils {

  public static String randomAlphanumeric(int length) {
    return (new Random()).ints(48, 123)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(length)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
