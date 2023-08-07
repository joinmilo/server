package app.wooportal.server.core.location.model;

import lombok.Data;

@Data
public class BingMapResult {

  protected String authenticationResultCode;
  protected String brandLogoUri;
  protected String copyright;
  protected Integer statusCode;
  protected String statusDescription;
  protected String traceId;
  
}
