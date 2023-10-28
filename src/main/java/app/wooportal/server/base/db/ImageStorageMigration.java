package app.wooportal.server.base.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.media.base.MimeTypeService;
import app.wooportal.server.core.media.storage.DefaultStorageConfiguration;
import app.wooportal.server.core.media.storage.DefaultStorageService;
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
  
  private MimeTypeService mimeTypeService = new MimeTypeService();

  @Override
  public String getConfirmationMessage() {
    return "Successfully migrated images";
  }

  @Override
  public void setUp() throws SetupException {
    var writeLocation = getWriteLocation();
    
    storageService = new DefaultStorageService(new DefaultStorageConfiguration(
        null, writeLocation));
  }

  private String getWriteLocation() {
    var location = System.getenv("WOOPORTAL_STORAGE_LOCATION");
    
    location = location != null && !location.isBlank()
        ? location
        : System.getProperty("storage.write-location");
    
    if (location == null || location.isBlank()) {
      var message = "No storage WRITE location found. Set either ENV WOOPORTAL_STORAGE_LOCATION or VM argument -Dstorage.write-location";
      errors.addError(message);
    } else {
      return location;
    }
    return null;
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
    
    var writeStatement = connection.prepareStatement("update media set size = ?, extension = ? where id = ?");
    connection.setAutoCommit(false);
    
    while (images.next()) {
      var id = images.getString(idField);
      var extension = mimeTypeService.getFileExtension(images.getString(mimeTypeField));
      
      // Write on disk
      var result = storageService.store(id, extension, images.getBytes(imageField));
      
      // Save file size in table
      writeStatement.setLong(1, result.length());
      writeStatement.setString(2, extension);
      writeStatement.setString(3, id);
      writeStatement.addBatch();
    }
    
    writeStatement.executeBatch();
    connection.commit();
  }
  
  private ResultSet retrieveData(
      String idField,
      String imageField,
      String mimeTypeField) throws DatabaseException, SQLException {
    var statement = connection.createStatement();
          
    return statement.executeQuery(
        String.format("select %1$s, %2$s, %3$s from media", idField, imageField, mimeTypeField));
  }

}
