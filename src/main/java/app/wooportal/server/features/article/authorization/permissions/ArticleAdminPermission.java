package app.wooportal.server.features.article.authorization.permissions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('article_admin', 'admin')")
public @interface ArticleAdminPermission { }
