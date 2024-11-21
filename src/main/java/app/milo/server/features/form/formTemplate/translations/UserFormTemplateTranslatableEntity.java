package app.milo.server.features.form.formTemplate.translations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.features.form.formTemplate.UserFormTemplateEntity;
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
@Table(name = "user_form_template_translatables")
public class UserFormTemplateTranslatableEntity extends TranslatableEntity<UserFormTemplateEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String content;

}
