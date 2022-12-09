package app.wooportal.server.core.media.base;

public class MediaHelper {
  
  public static String extractFormatFromMimeType(String mimeType) {
    String[] parts = mimeType.split("/");
    return parts.length > 1 ? parts[1] : parts[0];
  }

}
