package app.milo.server.features.form.templateType;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.features.form.templateType.translations.FormTemplateTypeTranslatableEntity;
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
@Table(name = "form_template_types")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class FormTemplateTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<FormTemplateTypeTranslatableEntity> translatables;


}
