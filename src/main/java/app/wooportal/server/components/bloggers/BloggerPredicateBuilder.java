package app.wooportal.server.components.bloggers;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class BloggerPredicateBuilder extends PredicateBuilder<QBloggerEntity, BloggerEntity> {

  public BloggerPredicateBuilder() {
    super(QBloggerEntity.bloggerEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }
}
