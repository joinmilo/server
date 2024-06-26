package app.milo.server.core.security.components.role.application.translation;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.milo.server.core.i18n.components.language.LanguageEntity;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.core.security.components.role.application.PrivilegeApplicationEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "privilege_application_translatables")
public class PrivilegeApplicationTranslatableEntity extends TranslatableEntity<PrivilegeApplicationEntity> {

  private static final long serialVersionUID = 1L;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private PrivilegeApplicationEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  private LanguageEntity language;
}
