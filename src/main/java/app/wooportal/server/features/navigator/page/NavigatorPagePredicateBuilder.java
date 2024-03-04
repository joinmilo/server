package app.wooportal.server.features.navigator.page;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class NavigatorPagePredicateBuilder extends PredicateBuilder<QNavigatorPageEntity, NavigatorPageEntity> {

  public NavigatorPagePredicateBuilder() {
    super(QNavigatorPageEntity.navigatorPageEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().title.likeIgnoreCase(term)
        .or(query.translatables.any().additionalInformation.likeIgnoreCase(term))
        .or(query.slug.likeIgnoreCase(term))
        .or(query.id.likeIgnoreCase(term));
  }
  
  public BooleanExpression withoutParentChoice() {
    return query.parentChoices.isEmpty();
  }
}
