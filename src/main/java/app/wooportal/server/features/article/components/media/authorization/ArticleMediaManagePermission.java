package app.wooportal.server.features.article.components.media.authorization;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("""
    hasAnyAuthority('articles_admin', 'admin')
      || (hasAuthority('articles_manage')
        && @articleMediaAuthorizationService.isOwn(authentication, #entity, #id))
""")
public @interface ArticleMediaManagePermission {

}
