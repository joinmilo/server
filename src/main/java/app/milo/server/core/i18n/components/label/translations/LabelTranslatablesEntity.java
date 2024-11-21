package app.milo.server.core.i18n.components.label.translations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.i18n.components.label.LabelEntity;
import app.milo.server.core.i18n.entities.TranslatableEntity;
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
@Table(name = "label_translatables")
public class LabelTranslatablesEntity extends TranslatableEntity<LabelEntity> {

  private static final long serialVersionUID = 1L;
  
  @Column(nullable = false)
  private String content;
}