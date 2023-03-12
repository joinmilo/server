package app.wooportal.server.base.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.media.base.MediaHelper;
import app.wooportal.server.core.media.storage.DefaultStorageService;
import app.wooportal.server.core.media.storage.StorageConfiguration;
import app.wooportal.server.core.media.storage.StorageService;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.DatabaseException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

@Service
public class ImageStorageMigration implements CustomTaskChange {

  private ValidationErrors errors = new ValidationErrors();
  
  private StorageService storageService;
  
  private JdbcConnection connection;

  @Override
  public String getConfirmationMessage() {
    return "Successfully migrated images";
  }

  @Override
  public void setUp() throws SetupException {
    var location = System.getenv("WOOPORTAL_STORAGE_LOCATION");
    
    location = location != null && !location.isBlank()
        ? location
        : System.getProperty("storage.location");
    
    if (location == null || location.isBlank()) {
      var message = "No storage location found. Set either ENV WOOPORTAL_STORAGE_LOCATION or VM argument -Dstorage.location";
      errors.addError(message);
    } else {
      storageService = new DefaultStorageService(new StorageConfiguration(location));
    }
  }

  @Override
  public void setFileOpener(ResourceAccessor resourceAccessor) { }

  @Override
  public ValidationErrors validate(Database database) {
    return errors;
  }

  @Override
  public void execute(Database database) throws CustomChangeException {
    connection = (JdbcConnection) database.getConnection();
    try {
      migrateImages();
    } catch (Exception e) {
      var sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      errors.addError("something went wrong: " + sw.toString());
      throw new CustomChangeException(sw.toString());
    }
  }

  private void migrateImages() throws DatabaseException, SQLException, IOException {
    var idField = "id";
    var imageField = "image";
    var mimeTypeField = "mime_type";
    
    var images = retrieveData(idField, imageField, mimeTypeField);
    
    while (images.next()) {
      storageService.store(
          images.getString(idField),
          MediaHelper.extractFormatFromMimeType(images.getString(mimeTypeField)),
          images.getBytes(imageField));
    }
  }
  
  private ResultSet retrieveData(
      String idField,
      String imageField,
      String mimeTypeField) throws DatabaseException, SQLException {
    var statement = connection.createStatement();
          
    return statement.executeQuery(
        String.format("select %1$s, %2$s, %3$s from images", idField, imageField, mimeTypeField));
  }

}
