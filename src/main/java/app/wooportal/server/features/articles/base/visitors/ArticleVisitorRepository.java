package app.wooportal.server.features.articles.base.visitors;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleVisitorRepository extends DataRepository<ArticleVisitorEntity> {

}
