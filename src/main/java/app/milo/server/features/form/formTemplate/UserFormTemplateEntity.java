package app.milo.server.features.form.formTemplate;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.base.userContext.base.UserContextEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.features.form.formTemplate.translations.UserFormTemplateTranslatableEntity;
import app.milo.server.features.form.templateType.FormTemplateTypeEntity;
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
@Table(name = "user_form_templates")
public class UserFormTemplateEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String content;

  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private UserContextEntity userContext;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private FormTemplateTypeEntity type;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<UserFormTemplateTranslatableEntity> translatables;

}
