package app.milo.server.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import app.milo.server.core.security.filter.JwtAuthorizationFilter;
import app.milo.server.core.security.services.AuthenticationService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

  private final AuthenticationService authService;

  private final BCryptPasswordEncoder bcryptPasswordEncoder;
  
  private final UserDetailsService userDetailsService;

  public ApplicationSecurity(
      AuthenticationService authService,
      BCryptPasswordEncoder encoder,
      UserDetailsService userDetailsService) {
    this.authService = authService;
    this.bcryptPasswordEncoder = encoder;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.userDetailsService).passwordEncoder(bcryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    .cors().and()
    .csrf().disable()
    .headers().frameOptions().sameOrigin()
      .and()
    .addFilter(jwtAuthorizationFilter())
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
  
  @Bean
  public GrantedAuthorityDefaults grantedAuthorityDefaults() {
    return new GrantedAuthorityDefaults("");
  }

  @Bean
  @Profile(value = { "development", "local", "test" })
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();

    config.setAllowCredentials(true);
    config.addAllowedOriginPattern(CorsConfiguration.ALL);
    config.addAllowedHeader(CorsConfiguration.ALL);
    config.addAllowedMethod(CorsConfiguration.ALL);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }

  public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
    return new JwtAuthorizationFilter(authService);
  }
  
  @Bean
  public AuthenticationManager getAuthManager() {
    var provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(bcryptPasswordEncoder);
    return new ProviderManager(provider);
  }
}
