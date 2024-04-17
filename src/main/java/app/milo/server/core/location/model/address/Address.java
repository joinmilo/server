package app.milo.server.core.location.model.address;

import lombok.Data;

@Data
public class Address {

  private String addressLine;
  private String adminDistrict;
  private String adminDistrict2;
  private String countryRegion;
  private String formattedAddress;
  private String locality;
  private String postalCode;
  
  private String houseNumber;
  private String street;
  
  public void setAddressLine(String addressLine) {
    this.addressLine = addressLine;
    setSplitAdressLine(addressLine);
  }

  private void setSplitAdressLine(String addressLine) {
    this.street = "";
    String[] addressParts = addressLine.split(" ");
    for (int i = 0; i < addressParts.length; i++) {
      if (i < addressParts.length - 1) {
        street = street +  " " + addressParts[i];
      } else {
        houseNumber = addressParts[i];
      }
    } 
    street = street.trim();
  }
}
