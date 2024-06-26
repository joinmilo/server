package app.milo.server.features.contest.base.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.features.contest.base.ContestEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "contest_translatables")
public class ContestTranslatableEntity extends TranslatableEntity<ContestEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String name;

  private String shortDescription;

}
