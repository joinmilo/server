package app.milo.server.features.contest.type.translations;

import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.features.contest.type.ContestTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "contest_type_translatables")
public class ContestTypeTranslatableEntity extends TranslatableEntity<ContestTypeEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;
}
