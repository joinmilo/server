package app.milo.server.features.article.components.base.visitors;

import org.springframework.stereotype.Repository;
import app.milo.server.core.visit.visitable.VisitableRepository;

@Repository
public interface ArticleVisitorRepository extends VisitableRepository<ArticleVisitorEntity> {

}
