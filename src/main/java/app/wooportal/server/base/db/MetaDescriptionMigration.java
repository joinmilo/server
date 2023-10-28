package app.wooportal.server.base.db;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.DatabaseException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

public class MetaDescriptionMigration implements CustomTaskChange {

  private ValidationErrors errors = new ValidationErrors();

  private JdbcConnection connection;

  @Override
  public String getConfirmationMessage() {
    return "Successfully migrated meta descriptions";
  }

  @Override
  public void setUp() throws SetupException {}

  @Override
  public void setFileOpener(ResourceAccessor resourceAccessor) {}

  @Override
  public ValidationErrors validate(Database database) {
    return errors;
  }

  @Override
  public void execute(Database database) throws CustomChangeException {
    connection = (JdbcConnection) database.getConnection();
    try {
      migrate("event_translatables", "events");
    } catch (Exception e) {
      var sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      errors.addError("something went wrong: " + sw.toString());
      throw new CustomChangeException(sw.toString());
    }
  }

  private void migrate(String tableNameData, String tableName)
      throws DatabaseException, SQLException {
    var idField = "id";
    var content = "short_description";

    var translatables = retrieveData(tableNameData, idField, content, tableName);

    var statement =
        connection.prepareStatement(String.format("update %1$s set %2$s = trim(?) where %3$s = ?",
            tableName, "meta_description", idField));
    connection.setAutoCommit(false);
    while (translatables.next()) {
      statement.setString(1, ((String) translatables.getObject(content)));
      statement.setObject(2, (String) translatables.getObject(idField));
      statement.addBatch();
    }
    statement.executeBatch();
    connection.commit();
  }

  private ResultSet retrieveData(String tableNameData, String idField, String name,
      String tableName) throws DatabaseException, SQLException {
    var statement = connection.createStatement();

    return statement.executeQuery(String.format(
        "SELECT feature.%1$s, translatable.%2$s FROM %4$s feature "
            + "JOIN %3$s translatable ON feature.id = translatable.parent_id "
            + "JOIN languages l ON translatable.language_id = l.id " + "WHERE l.locale = 'en'",
        idField, name, tableNameData, tableName));
  }
}
