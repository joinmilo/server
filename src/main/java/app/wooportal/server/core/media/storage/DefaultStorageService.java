package app.wooportal.server.core.media.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.error.exception.NotFoundException;

@Service
public class DefaultStorageService implements StorageService {
  
  private final StorageConfiguration storageConfig;
  
  public DefaultStorageService(
      StorageConfiguration storageConfig) {
    this.storageConfig = storageConfig;
  }
  
  @Override
  public void delete(String id, String extension) {
    createFile(id, extension).delete();
  }
  
  @Override
  public byte[] read(String id, String extension) throws IOException {
    var file = createFile(id, extension);
    if (!file.exists()) {
      throw new NotFoundException("Media file does not exist", file.getName());
    }
    return Files.readAllBytes(file.toPath());
  }

  @Override
  public File store(String id, String extension, byte[] data) throws IOException {
    Files.createDirectories(Paths.get(storageConfig.getLocation()));
    var file = createFile(id, extension);
    Files.write(file.toPath(), data);
    
    return file;
  }
  
  private File createFile(String id, String extension) {
    var base = storageConfig.getLocation().endsWith("/")
        ? storageConfig.getLocation()
        : storageConfig.getLocation() + "/";
    return new File(base + id +  "." + extension);
  }

}
