package app.wooportal.server.base.cms.authorization.permissions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('cms_admin', 'admin')")
public @interface CmsAdminPermission {}
