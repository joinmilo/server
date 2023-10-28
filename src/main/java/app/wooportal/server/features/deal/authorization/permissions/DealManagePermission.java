package app.wooportal.server.features.deal.authorization.permissions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("""
    hasAnyAuthority('deals_admin', 'admin')
      || (hasAuthority('deals_manage')
        && @dealAuthorizationService.isOwn(authentication, #entity, #entities, #id, #ids))
""")
public @interface DealManagePermission {

}
