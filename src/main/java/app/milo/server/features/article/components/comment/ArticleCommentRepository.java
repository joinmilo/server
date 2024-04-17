package app.milo.server.features.article.components.comment;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface ArticleCommentRepository extends DataRepository<ArticleCommentEntity> {

}
