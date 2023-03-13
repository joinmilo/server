package app.wooportal.server.base.feedback.type.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.base.developer.DeveloperEntity;
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
@Table(name = "feedback_type_translatables")
public class FeedbackTypeTranslatableEntity extends TranslatableEntity<DeveloperEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;
  
}