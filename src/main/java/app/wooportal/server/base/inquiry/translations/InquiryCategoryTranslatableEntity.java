package app.wooportal.server.base.inquiry.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.base.developers.DeveloperEntity;
import app.wooportal.server.base.inquiry.category.InquiryCategoryEntity;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.i18n.language.LanguageEntity;
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
@Table(name = "inquiry_category_translatables")
public class InquiryCategoryTranslatableEntity extends TranslatableEntity<DeveloperEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private LanguageEntity language;

  @ManyToOne(fetch = FetchType.LAZY)
  private InquiryCategoryEntity parent;
}
