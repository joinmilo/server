package app.milo.server.core.i18n.components.label.authorization.permissions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('translator_admin', 'admin')")
public @interface TranslatorAdminPermission {}
