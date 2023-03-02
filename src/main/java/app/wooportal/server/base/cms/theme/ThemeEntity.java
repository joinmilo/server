package app.wooportal.server.base.cms.theme;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.base.cms.themeVariable.ThemeVariableEntity;
import app.wooportal.server.core.base.BaseEntity;
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

  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme")
  private Set<ThemeVariableEntity> themeVariables;
}
