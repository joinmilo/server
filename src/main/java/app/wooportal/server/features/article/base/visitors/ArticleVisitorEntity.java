package app.wooportal.server.features.article.base.visitors;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.core.visit.visitable.VisitableEntity;
import app.wooportal.server.features.article.base.ArticleEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "article_visitors")
public class ArticleVisitorEntity extends VisitableEntity<ArticleEntity> {

  private static final long serialVersionUID = 1L;

}
