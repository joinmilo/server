package app.wooportal.server.features.contests.types.translations;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
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
public class ContestTypeTranslatableEntity extends TranslatableEntity<ContestTypeTranslatableEntity> {

  private static final long serialVersionUID = 1L;

  private String name;
}
