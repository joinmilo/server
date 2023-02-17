package app.wooportal.server.features.articles.ratings;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface ArticleRatingRepository extends DataRepository<ArticleRatingEntity> {

}
