package app.wooportal.server.features.article.base.media;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.article.base.ArticleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "article_media")
public class ArticleMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  private Boolean card = false;
  
  private Boolean title = false;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private ArticleEntity article;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;
  
}
