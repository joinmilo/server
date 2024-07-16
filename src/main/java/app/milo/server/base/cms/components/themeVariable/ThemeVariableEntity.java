package app.milo.server.base.cms.components.themeVariable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import app.milo.server.base.cms.components.theme.ThemeEntity;
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
@Table(name = "theme_variables")
public class ThemeVariableEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Column(nullable = false)
  private String code;
  
  @Column(nullable = false)
  private String value;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ThemeEntity theme;
  
}
