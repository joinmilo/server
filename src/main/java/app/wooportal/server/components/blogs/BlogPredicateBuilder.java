package app.wooportal.server.components.blogs;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class BlogPredicateBuilder extends PredicateBuilder<QBlogEntity, BlogEntity> {

  public BlogPredicateBuilder() {
    super(QBlogEntity.blogEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.author.likeIgnoreCase(term)
        .or(query.content.likeIgnoreCase(term))
        .or(query.title.likeIgnoreCase(term))
        .or(query.topicId.likeIgnoreCase(term));
  }
}
