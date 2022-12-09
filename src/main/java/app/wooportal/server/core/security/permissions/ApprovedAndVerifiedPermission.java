package app.wooportal.server.core.security.permissions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("""
      @authorizationService.isApproved(authentication) 
        or @authorizationService.isVerified(authentication)
    """)
public @interface ApprovedAndVerifiedPermission {

}
