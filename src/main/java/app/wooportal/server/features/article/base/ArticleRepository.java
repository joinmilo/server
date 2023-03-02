package app.wooportal.server.features.article.base;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface ArticleRepository extends DataRepository<ArticleEntity> {

}
