package app.milo.server.base.cms.components.theme;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.base.cms.components.themeVariable.ThemeVariableEntity;
import app.milo.server.core.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "themes")
public class ThemeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Boolean isDefault;

  @Column(nullable = false, unique = true)
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme")
  private Set<ThemeVariableEntity> variables;
}
