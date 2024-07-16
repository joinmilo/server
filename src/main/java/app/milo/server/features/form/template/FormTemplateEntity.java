package app.milo.server.features.form.template;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.features.form.template.translations.FormTemplateTranslatableEntity;
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
@Table(name = "form_templates")
public class FormTemplateEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String content;
  
  @Translatable
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<FormTemplateTranslatableEntity> translatables;
}
