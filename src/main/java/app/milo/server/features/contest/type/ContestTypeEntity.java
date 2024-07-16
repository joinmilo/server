package app.milo.server.features.contest.type;

import java.util.Set;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.features.contest.base.ContestEntity;
import app.milo.server.features.contest.type.translations.ContestTypeTranslatableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "contest_types")
public class ContestTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String code;
  
  @Translatable
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
  private Set<ContestEntity> contests;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<ContestTypeTranslatableEntity> translatables;
}
