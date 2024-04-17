package app.milo.server.core.utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MessageUtils {

  public static String createMessage(String message, Object... params) {
    return String.format(
        message + ": %1$s, params: %2$s",
        message,
        Stream.of(params)
            .map(p -> p != null ? p.toString() : null)
            .collect(Collectors.joining(", ")));
  }
}
