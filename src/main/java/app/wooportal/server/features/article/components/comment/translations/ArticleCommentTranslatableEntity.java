package app.wooportal.server.features.article.components.comment.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.features.article.components.comment.ArticleCommentEntity;
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
@Table(name = "article_comment_translatables")
public class ArticleCommentTranslatableEntity extends TranslatableEntity<ArticleCommentEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String content;
  
}
