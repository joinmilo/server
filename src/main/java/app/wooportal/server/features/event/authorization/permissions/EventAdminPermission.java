package app.wooportal.server.features.event.authorization.permissions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('events_admin', 'admin')")
public @interface EventAdminPermission { }
