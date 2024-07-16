package app.milo.server.core.i18n.components.language;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.base.BaseEntity;
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
@Table(name = "languages")
public class LanguageEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  private Boolean active;

  @Column(nullable = false, unique = true)
  private String locale;

  @Column(nullable = false, unique = true)
  private String name;

}
