package app.wooportal.server.base.newsFeed;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class NewsFeedPredicateBuilder extends PredicateBuilder<QNewsFeedEntity, NewsFeedEntity> {

  public NewsFeedPredicateBuilder() {
    super(QNewsFeedEntity.newsFeedEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
