package app.wooportal.server.features.articles.base.visitors;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.visit.visitable.VisitableRepository;

@Repository
public interface ArticleVisitorRepository extends VisitableRepository<ArticleVisitorEntity> {

}
