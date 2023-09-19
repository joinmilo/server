package app.wooportal.server.base.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.regex.Pattern;
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
public class VideoMigration implements CustomTaskChange {

  private ValidationErrors errors = new ValidationErrors();
  
  private JdbcConnection connection;

  @Override
  public String getConfirmationMessage() {
    return "Successfully migrated images";
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
      migrateOrganisationVideos();
      migratePageVideos();
    } catch (Exception e) {
      var sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      errors.addError("something went wrong: " + sw.toString());
      throw new CustomChangeException(sw.toString());
    }
  }

  private void migrateOrganisationVideos() throws DatabaseException, SQLException, IOException {    
    var statement = connection.createStatement();
    var result = statement.executeQuery("""
        SELECT
            ov.id as "ov.id",
            v.id as "v.id",
            ov.organisation_id,
            v.created,
            v.modified,
            v.thumbnail_id,
            v.url
        FROM organisation_videos ov
        LEFT JOIN videos v on v.id = ov.video_id;
      """);
    
    var writeMediaStatement = connection.prepareStatement(
        "INSERT INTO media (id, created, modified, thumbnail_id, url, mime_type, extension) VALUES (?, ?, ?, ?, ?, 'video/mp4', 'mp4');");
    var writeOrganisationMediaStatement = connection.prepareStatement(
        "INSERT INTO organisation_media (id, created, modified, organisation_id, media_id) VALUES (?, ?, ?, ?, ?);");
    
    connection.setAutoCommit(true);
    
    while (result.next()) {
      var url = result.getString("url");
      
      if (isValidYoutubeLink(url)) {
        writeMediaStatement.setString(1, result.getString("v.id"));
        writeMediaStatement.setString(2, result.getString("created"));
        writeMediaStatement.setString(3, result.getString("modified"));
        writeMediaStatement.setString(4, result.getString("thumbnail_id"));
        writeMediaStatement.setString(5, url.replace("watch?v=","embed/").replace("youtu.be", "youtube.com/embed"));
        
        writeMediaStatement.executeUpdate();
        
        writeOrganisationMediaStatement.setString(1, result.getString("ov.id"));
        writeOrganisationMediaStatement.setString(2, result.getString("created"));
        writeOrganisationMediaStatement.setString(3, result.getString("modified"));
        writeOrganisationMediaStatement.setString(4, result.getString("organisation_id"));
        writeOrganisationMediaStatement.setString(5, result.getString("v.id"));
        
        writeOrganisationMediaStatement.executeUpdate();
      }
    }
  }
  
  private void migratePageVideos() throws DatabaseException, SQLException, IOException {    
    var statement = connection.createStatement();
    var result = statement.executeQuery("""
        SELECT
            mv.id as "mv.id",
            v.id as "v.id",
            mv.markup_id,
            v.created,
            v.modified,
            v.thumbnail_id,
            v.url
        FROM markup_videos mv
        LEFT JOIN videos v on v.id = mv.video_id;
      """);
    
    var writeMediaStatement = connection.prepareStatement(
        "INSERT INTO media (id, created, modified, thumbnail_id, url, mime_type, extension) VALUES (?, ?, ?, ?, ?, 'video/mp4', 'mp4');");
    var writePageMediaStatement = connection.prepareStatement(
        "INSERT INTO page_media (id, created, modified, page_id, media_id) VALUES (?, ?, ?, ?, ?);");
    
    connection.setAutoCommit(true);
    
    while (result.next()) {
      var url = result.getString("url");
      
      if (isValidYoutubeLink(url)) {
        writeMediaStatement.setString(1, result.getString("v.id"));
        writeMediaStatement.setString(2, result.getString("created"));
        writeMediaStatement.setString(3, result.getString("modified"));
        writeMediaStatement.setString(4, result.getString("thumbnail_id"));
        writeMediaStatement.setString(5, url.replace("watch?v=","embed/").replace("youtu.be", "youtube.com/embed"));
        
        writeMediaStatement.executeUpdate();
        
        writePageMediaStatement.setString(1, result.getString("mv.id"));
        writePageMediaStatement.setString(2, result.getString("created"));
        writePageMediaStatement.setString(3, result.getString("modified"));
        writePageMediaStatement.setString(4, result.getString("markup_id"));
        writePageMediaStatement.setString(5, result.getString("v.id"));
        
        writePageMediaStatement.executeUpdate();
      }
    }
  }
  
  private boolean isValidYoutubeLink(String url) {
    var youtubeRegex = "^(?:https?://)?(?:www\\.)?(?:youtube\\.com|youtu\\.be)/(?:watch\\?v=|embed/|v/)?([a-zA-Z0-9_-]{11})";
    var pattern = Pattern.compile(youtubeRegex);
    var matcher = pattern.matcher(url);
    return matcher.matches();
  }

}
