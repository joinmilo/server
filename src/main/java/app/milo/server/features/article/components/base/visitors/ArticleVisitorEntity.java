package app.milo.server.features.article.components.base.visitors;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.visit.visitable.VisitableEntity;
import app.milo.server.features.article.components.base.ArticleEntity;
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
