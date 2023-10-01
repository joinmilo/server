package app.wooportal.server.features.article.components.category.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.features.article.components.category.ArticleCategoryEntity;
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
@Table(name = "article_category_translatables", uniqueConstraints = 
@UniqueConstraint(columnNames = { "parent_id", "language_id" }))
public class ArticleCategoryTranslatableEntity extends TranslatableEntity<ArticleCategoryEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;
}
