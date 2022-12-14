package app.wooportal.server.features.forms.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.language.LanguageEntity;
import app.wooportal.server.core.i18n.language.entities.TranslatableEntity;
import app.wooportal.server.features.events.base.EventEntity;
import app.wooportal.server.features.forms.formTemplateType.FormTemplateTypeEntity;
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
@Table(name = "form_template_type_translatables")
public class FormTemplateTypeTranslatableEntity extends TranslatableEntity<EventEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private FormTemplateTypeEntity formTemplateType;

  @ManyToOne(fetch = FetchType.LAZY)
  private LanguageEntity language;
}
