package app.wooportal.server.base.db;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.DatabaseException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

@Service
public class ShortDescriptionMigration implements CustomTaskChange {

  private ValidationErrors errors = new ValidationErrors();
  
  private JdbcConnection connection;

  @Override
  public String getConfirmationMessage() {
    return "Successfully migrated short descriptions for events and articles";
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
    connection = (JdbcConnection) database.getConnection();
    try {
      migrate("event_translatables");
      migrate("article_translatables");
    } catch (Exception e) {
      var sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      errors.addError("something went wrong: " + sw.toString());
      throw new CustomChangeException(sw.toString());
    }
  }

  private void migrate(String tableName) throws DatabaseException, SQLException {
    var idField = "id";
    var descriptionField = tableName == "event_translatables" ? "description" : "content";
    
    var translatables = retrieveData(tableName, idField, descriptionField);
    
    var statement = connection.prepareStatement(
        String.format("update %1$s set %2$s = trim(?) where %3$s = ?", tableName, "short_description", idField));
    connection.setAutoCommit(false);
    while (translatables.next()) {
      statement.setString(1, prepareShortDescription((String) translatables.getObject(descriptionField)));
      statement.setObject(2, (String) translatables.getObject(idField));
      statement.addBatch();
    }
  
    statement.executeBatch();
    connection.commit();
  }

  private ResultSet retrieveData(
      String tableName,
      String idField,
      String descriptionField) throws DatabaseException, SQLException {
    var statement = connection.createStatement();
          
    return statement.executeQuery(
        String.format("select %1$s, %2$s from %3$s", idField, descriptionField, tableName));
  }
  
  private String prepareShortDescription(String content) {
    var result = Jsoup.parse(content).body().text();
    return result.substring(0, Math.min(result.length(), 200)).strip().stripIndent();
  }

}
