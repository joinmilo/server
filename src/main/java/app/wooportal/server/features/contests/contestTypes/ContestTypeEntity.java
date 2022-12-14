package app.wooportal.server.features.contests.contestTypes;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.contests.base.ContestEntity;
import app.wooportal.server.features.contests.translations.ContestTypeTranslatableEntity;
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
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ContestTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private String key;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
  private Set<ContestEntity> contests;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<ContestTypeTranslatableEntity> translatable;
}
