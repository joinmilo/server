package app.wooportal.server.base.db;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import app.wooportal.server.core.base.PredicateBuilder;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.seo.SlugService;
import app.wooportal.server.features.event.base.EventPredicateBuilder;
import app.wooportal.server.features.event.base.EventRepository;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.DatabaseException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

public class SlugMigration implements CustomTaskChange {

  private SlugService slugService;

  public SlugMigration() {
    this.slugService = new SlugService();
  }

  private ValidationErrors errors = new ValidationErrors();

  private JdbcConnection connection;

  @Override
  public String getConfirmationMessage() {
    return "Successfully migrated Slugs";
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
    var name = "name";

    var translatables = retrieveData(tableNameData, idField, name, tableName);

    var statement = connection.prepareStatement(
        String.format("update %1$s set %2$s = trim(?) where %3$s = ?", tableName, "slug", idField));
    while (translatables.next()) {
      statement.setString(1, prepareSlug((String) translatables.getObject(name), tableName));
      statement.setObject(2, (String) translatables.getObject(idField));
      statement.addBatch();
      statement.executeUpdate();
    }
  }

  private ResultSet retrieveData(String tableNameData, String idField, String name,
      String tableName) throws DatabaseException, SQLException {
    var statement = connection.createStatement();

    return statement.executeQuery(String.format(
        "SELECT feature.%1$s, translatable.%2$s FROM %4$s feature "
            + "JOIN %3$s translatable ON feature.id = translatable.parent_id "
            + "JOIN languages l ON translatable.language_id = l.id " + "WHERE l.locale = 'de'",
        idField, name, tableNameData, tableName));
  }

  private String prepareSlug(String content, String tableName)
      throws DatabaseException, SQLException {

    var slug = slugService.slugify(content);
    var uniqueSlug = slug;
    Integer i = 1;

    while (slugExists(uniqueSlug, tableName)) {
      uniqueSlug = slug + "-" +i++;
    }
    return uniqueSlug;
  }

  private boolean slugExists(String slug, String tableName) throws SQLException, DatabaseException {
    var statement = connection.createStatement();

    ResultSet resultSet = statement.executeQuery(String
        .format("SELECT EXISTS (SELECT 1 FROM %1$s WHERE slug = '%2$s')", tableName, slug));

    return resultSet.next() && resultSet.getBoolean(1);
  }
}
