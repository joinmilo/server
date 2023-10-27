package app.wooportal.server.features.deal.authorization.permissions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('deals_admin', 'admin')")
public @interface DealAdminPermission { }
