package app.wooportal.server.features.article.rating;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.base.analytics.rating.RatableEntity;
import app.wooportal.server.features.article.base.ArticleEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "article_ratings")
public class ArticleRatingEntity extends RatableEntity<ArticleEntity> {

  private static final long serialVersionUID = 1L;

}
