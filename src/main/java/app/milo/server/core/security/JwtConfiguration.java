package app.milo.server.core.security;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt")
@Configuration
public class JwtConfiguration {

  @DurationUnit(ChronoUnit.HOURS)
  private Duration expirationTimeAccess = Duration.ofHours(12);
  
  @DurationUnit(ChronoUnit.HOURS)
  private Duration expirationTimeRefresh = Duration.ofHours(12);

  private String secret;
  private String claimApproved;
  private String claimPrivileges;
  private String claimUserid;
  private String claimScopes;
  private String claimVerified;
  private String scopeRefresh;
  private String scopeAccess;
  private String scopeApi;
}
