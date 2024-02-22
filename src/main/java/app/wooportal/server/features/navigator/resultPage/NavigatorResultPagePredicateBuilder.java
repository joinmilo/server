package app.wooportal.server.features.navigator.resultPage;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class NavigatorResultPagePredicateBuilder extends PredicateBuilder<QNavigatorResultPageEntity, NavigatorResultPageEntity> {

  public NavigatorResultPagePredicateBuilder() {
    super(QNavigatorResultPageEntity.navigatorResultPageEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().title.likeIgnoreCase(term)
        .or(query.translatables.any().content.likeIgnoreCase(term))
        .or(query.slug.likeIgnoreCase(term))
        .or(query.id.likeIgnoreCase(term));
  }
  
}
