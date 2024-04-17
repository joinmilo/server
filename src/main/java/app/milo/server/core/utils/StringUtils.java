package app.milo.server.core.utils;

import java.util.Random;

public class StringUtils {

  public static String randomAlphanumeric(int length) {
    return (new Random()).ints(48, 123)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(length)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
  
  public static String replaceUmlauts(String input) {
    return input == null
         ? null
         : input.replace("ü", "ue")
            .replace("ö", "oe")
            .replace("ä", "ae")
            .replace("ß", "ss")
            .replaceAll("Ü(?=[a-zäöüß ])", "Ue")
            .replaceAll("Ö(?=[a-zäöüß ])", "Oe")
            .replace("Ü", "UE")
            .replace("Ö", "OE")
            .replace("Ä", "AE")
            .replace("\u00fc", "ue")
            .replace("\u00f6", "oe")
            .replace("\u00e4", "ae")
            .replace("\u00df", "ss")
            .replaceAll("\u00dc(?=[a-z\u00e4\u00f6\u00fc\u00df ])", "Ue")
            .replaceAll("\u00d6(?=[a-z\u00e4\u00f6\u00fc\u00df ])", "Oe")
            .replaceAll("\u00c4(?=[a-z\u00e4\u00f6\u00fc\u00df ])", "Ae")
            .replace("\u00dc", "UE")
            .replace("\u00d6", "OE")
            .replace("\u00c4", "AE");
  }
}
