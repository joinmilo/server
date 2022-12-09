package app.wooportal.server.core.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import app.wooportal.server.core.security.services.AuthenticationService;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private final AuthenticationService authService;

  public JwtAuthorizationFilter(AuthenticationService authService) {
    super(authService.getAuthManager());
    this.authService = authService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader("Authorization");
    if (header == null || !header.startsWith("Bearer ")) {
      chain.doFilter(req, res);
      return;
    }
    
    var authentication = authService.getAuthenticationToken(req);
    if (authentication.isPresent()) {
      SecurityContextHolder.getContext().setAuthentication(authentication.get());
    }
    chain.doFilter(req, res);
  }
}
