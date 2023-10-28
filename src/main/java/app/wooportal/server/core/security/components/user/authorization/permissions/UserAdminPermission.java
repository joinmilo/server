package app.wooportal.server.core.security.components.user.authorization.permissions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('user_admin', 'admin')")
public @interface UserAdminPermission {}
