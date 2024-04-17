package app.milo.server.features.article.components.base.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.features.article.components.base.ArticleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "article_translatables")
public class ArticleTranslatableEntity extends TranslatableEntity<ArticleEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false) 
  private String content;

  @Column(nullable = false)
  private String name;
  
  private String shortDescription;

}
