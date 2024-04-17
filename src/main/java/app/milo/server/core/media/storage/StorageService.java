package app.milo.server.core.media.storage;

import java.io.File;
import java.io.IOException;
import app.milo.server.core.error.exception.NotFoundException;

public interface StorageService {
  
  public void delete(String id, String extension);
  
  public byte[] read(String id, String extension) throws IOException, NotFoundException;

  public File store(String id, String extension, byte[] data) throws IOException;
  
  public String getReadLocation(String id);
  
}
