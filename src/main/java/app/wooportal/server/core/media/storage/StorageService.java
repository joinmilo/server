package app.wooportal.server.core.media.storage;

import java.io.File;
import java.io.IOException;
import app.wooportal.server.core.error.exception.NotFoundException;

public interface StorageService {
  
  public void delete(String id, String extension);
  
  public byte[] read(String id, String extension) throws IOException, NotFoundException;

  public File store(String id, String extension, byte[] data) throws IOException;
  
  public String getReadLocation(String id);
  
}
