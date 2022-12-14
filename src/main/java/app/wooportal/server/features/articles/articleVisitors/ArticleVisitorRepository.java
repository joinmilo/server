package app.wooportal.server.features.articles.articleVisitors;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleVisitorRepository extends DataRepository<ArticleVisitorEntity> {

}
