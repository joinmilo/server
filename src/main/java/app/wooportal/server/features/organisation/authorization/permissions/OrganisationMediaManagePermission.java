package app.wooportal.server.features.organisation.authorization.permissions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("""
    hasAnyAuthority('organisations_admin', 'admin')
      || (hasAuthority('organisations_manage')
        && @articleMediaAuthorizationService.isOwn(authentication, #entity, #entities, #id, #ids))
""")
public @interface OrganisationMediaManagePermission {

}
