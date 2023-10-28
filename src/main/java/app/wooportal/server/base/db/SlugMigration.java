package app.wooportal.server.base.db;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.wooportal.server.core.seo.SlugService;
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
      migrateFromTranslatable("article_translatables", "articles");
      migrateFromTranslatable("event_translatables", "events");
      migrateFromTranslatable("page_translatables", "pages");
      
      migrateOrganisation();
      
      migrateUserContext();
    } catch (Exception e) {
      var sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      errors.addError("something went wrong: " + sw.toString());
      throw new CustomChangeException(sw.toString());
    }
  }

  private void migrateFromTranslatable(String sourceTable, String targetTable)
      throws DatabaseException, SQLException {
    executeMigration(
        targetTable,
        retrieveTranslatables(sourceTable, targetTable),
        "id",
        "name");
  }
  
  private ResultSet retrieveTranslatables(
      String tableNameData,
      String tableName) throws DatabaseException, SQLException {
    var statement = connection.createStatement();

    return statement.executeQuery(String.format(
        "SELECT feature.id, translatable.name FROM %2$s feature "
            + "JOIN %1$s translatable ON feature.id = translatable.parent_id "
            + "JOIN languages l ON translatable.language_id = l.id " + "WHERE l.locale = 'de'",
        tableNameData, tableName));
  }
  
  private void migrateOrganisation() throws DatabaseException, SQLException {
    var organisations = connection
        .createStatement()
        .executeQuery("SELECT id, name FROM organisations");
    
    executeMigration("organisations", organisations, "id", "name");
  }
  
  private void migrateUserContext() throws DatabaseException, SQLException {
    var users = connection
        .createStatement()
        .executeQuery("""
                SELECT uc.id, u.first_name, u.last_name FROM user_contexts uc
                left join users u on u.id = uc.user_id
                """);
    
    executeMigration("user_contexts", users, "uc.id", "u.first_name", "u.last_name");
  }

  private void executeMigration(String targetTable, ResultSet source, String id, String... sourceFields)
      throws SQLException, DatabaseException {
    var statement = connection.prepareStatement(
        String.format("update %1$s set slug = trim(?) where id = ?", targetTable));
    while (source.next()) {
      var slugSource = "";
      for (var sourceField: sourceFields) {
        slugSource = slugSource + " " + (String) source.getObject(sourceField);
      }
      statement.setString(1, slugify(slugSource, targetTable));
      statement.setObject(2, (String) source.getObject(id));
      statement.addBatch();
      statement.executeUpdate();
    }
  }

  private String slugify(String content, String tableName)
      throws DatabaseException, SQLException {

    var slug = slugService.slugify(content == null || content.contains("null") ? tableName : content);
    var i = 1;

    while (slugExists(slug, tableName)) {
      slug = slug + "-" + i++;
    }
    return slug;
  }

  private boolean slugExists(String slug, String tableName) throws SQLException, DatabaseException {
    var statement = connection.createStatement();

    var resultSet = statement.executeQuery(String
        .format("SELECT EXISTS (SELECT 1 FROM %1$s WHERE slug = '%2$s')", tableName, slug));

    return resultSet.next() && resultSet.getBoolean(1);
  }
}
