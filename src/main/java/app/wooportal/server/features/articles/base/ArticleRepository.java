package app.wooportal.server.features.articles.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends DataRepository<ArticleEntity> {

}
