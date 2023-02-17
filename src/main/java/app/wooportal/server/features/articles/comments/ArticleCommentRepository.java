package app.wooportal.server.features.articles.comments;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface ArticleCommentRepository extends DataRepository<ArticleCommentEntity> {

}
