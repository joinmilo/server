package app.milo.server.features.article.components.category.translations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.features.article.components.category.ArticleCategoryEntity;
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
