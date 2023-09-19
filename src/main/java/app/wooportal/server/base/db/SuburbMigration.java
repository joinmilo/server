package app.wooportal.server.base.db;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import javax.naming.ServiceUnavailableException;
import app.wooportal.server.base.address.base.AddressEntity;
import app.wooportal.server.base.address.suburb.SuburbEntity;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.location.BingMapService;
import app.wooportal.server.core.location.LocationConfiguration;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.DatabaseException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

public class SuburbMigration implements CustomTaskChange {

  private  BingMapService bingMapService;

  private ValidationErrors errors = new ValidationErrors();

  private JdbcConnection connection;
  
  private String place;

  @Override
  public String getConfirmationMessage() {
    return "Successfully migrated Slugs";
  }
  
  @Override
  public void setUp() throws SetupException {
    var serviceSubscriptionKey = getLocationKey();
    var locationConfig = new LocationConfiguration();
    locationConfig.setServiceSubscriptionKey(serviceSubscriptionKey);
    locationConfig.setAddressUrl("http://dev.virtualearth.net/REST/v1/Locations/DE");
    bingMapService = new BingMapService(locationConfig, null);
    
    place = getPlace();
  }

  private String getLocationKey() {
    var place = System.getenv("WOOPORTAL_LOCATION_KEY");
    
    place = place != null && !place.isBlank()
        ? place
        : System.getProperty("location.key");
    
    if (place == null || place.isBlank()) {
      var message = "No location key found. Set either ENV WOOPORTAL_LOCATION_KEY or VM argument -Dlocation.key";
      errors.addError(message);
    } else {
      return place;
    }
    return null;
  }
  
  private String getPlace() {
    var location = System.getenv("WOOPORTAL_LOCATION_CITY");
    
    location = location != null && !location.isBlank()
        ? location
        : System.getProperty("location.place");
    
    if (location == null || location.isBlank()) {
      var message = "No location key found. Set either ENV WOOPORTAL_LOCATION_CITY or VM argument -Dlocation.place";
      errors.addError(message);
    } else {
      return location;
    }
    return null;
  }

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
      migrateSuburb();
    } catch (Exception e) {
      var sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      errors.addError("something went wrong: " + sw.toString());
      throw new CustomChangeException(sw.toString());
    }
  }

  private void migrateSuburb() throws DatabaseException, SQLException {
    var suburbsResultSet =
        connection.createStatement().executeQuery("SELECT id, name FROM suburbs");

    var updateStatement =
        connection.prepareStatement("UPDATE suburbs SET longitude = ?, latitude = ? WHERE id = ?");

    while (suburbsResultSet.next()) {
      var suburbId = suburbsResultSet.getString("id");

      var suburb = new SuburbEntity();
      suburb.setName(suburbsResultSet.getString("name"));
      var address = new AddressEntity();
      address.setPlace(place);
      address.setSuburb(suburb);

      try {
    
        var updatedAddress = bingMapService.retrieveExternalAddress(address);

        var longitude = updatedAddress.getLongitude();
        var latitude = updatedAddress.getLatitude();

        updateStatement.setFloat(1, longitude);
        updateStatement.setFloat(2, latitude);
        updateStatement.setString(3, suburbId);

        updateStatement.addBatch();
      } catch (ServiceUnavailableException | NotFoundException e) {
      }
    }

    updateStatement.executeBatch();
  }
}
