package app.milo.server.features.article.components.rating;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface ArticleRatingRepository extends DataRepository<ArticleRatingEntity> {

}
