package app.wooportal.server.base.db;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.media.storage.StorageService;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

@Service
public class ImageStorageMigration implements CustomTaskChange {

  private ValidationErrors errors = new ValidationErrors();
  
  @Autowired
  private StorageService storageService;

  @Override
  public String getConfirmationMessage() {
    return "Successfully migrated rejects to inspection items";
  }

  @Override
  public void setUp() throws SetupException { }

  @Override
  public void setFileOpener(ResourceAccessor resourceAccessor) { }

  @Override
  public ValidationErrors validate(Database database) {
    return errors;
  }

  @Override
  public void execute(Database database) throws CustomChangeException {
    var connection = (JdbcConnection) database.getConnection();
    try {
      System.out.println(storageService.toString());
    } catch (Exception e) {
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      errors.addError("something went wrong: " + sw.toString());
    }
  }

}
