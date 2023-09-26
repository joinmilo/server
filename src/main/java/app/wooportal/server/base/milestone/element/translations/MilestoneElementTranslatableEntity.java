package app.wooportal.server.base.milestone.element.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.base.milestone.element.MilestoneElementEntity;
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
@Table(name = "milestone_element_translatables")
public class MilestoneElementTranslatableEntity extends TranslatableEntity<MilestoneElementEntity> {

  private static final long serialVersionUID = 1L;
  
  @Column(nullable = false)
  private String name;
  
  @Column(nullable = false)
  private String description;

}
