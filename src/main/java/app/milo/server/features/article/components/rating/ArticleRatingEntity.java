package app.milo.server.features.article.components.rating;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import app.milo.server.base.analytics.rating.RatableEntity;
import app.milo.server.features.article.components.base.ArticleEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "article_ratings", uniqueConstraints = 
@UniqueConstraint(columnNames = { "parent_id", "user_context_id" }))
public class ArticleRatingEntity extends RatableEntity<ArticleEntity> {

  private static final long serialVersionUID = 1L;

}
