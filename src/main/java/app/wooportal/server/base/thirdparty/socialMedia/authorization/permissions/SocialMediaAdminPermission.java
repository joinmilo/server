package app.wooportal.server.base.thirdparty.socialMedia.authorization.permissions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('social_media_admin', 'admin')")
public @interface SocialMediaAdminPermission {}
