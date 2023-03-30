package app.wooportal.server.core.captcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaResponse {
  
  private Boolean success;
  
  @JsonProperty(value = "error-codes")
  private String[] errorCodes;

}
