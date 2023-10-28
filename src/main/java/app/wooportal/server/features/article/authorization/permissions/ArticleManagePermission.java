package app.wooportal.server.features.article.authorization.permissions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("""
    hasAnyAuthority('articles_admin', 'admin')
      || (hasAuthority('articles_manage')
        && @articleAuthorizationService.isOwn(authentication, #entity, #entities, #id, #ids))
""")
public @interface ArticleManagePermission {

}
