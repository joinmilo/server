package app.milo.server.features.article.components.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface ArticleRepository extends DataRepository<ArticleEntity> {

}
