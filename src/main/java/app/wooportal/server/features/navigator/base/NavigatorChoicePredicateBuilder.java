package app.wooportal.server.features.navigator.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class NavigatorChoicePredicateBuilder extends PredicateBuilder<QNavigatorChoiceEntity, NavigatorChoiceEntity> {

  public NavigatorChoicePredicateBuilder() {
    super(QNavigatorChoiceEntity.navigatorChoiceEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().name.likeIgnoreCase(term)
        .or(query.translatables.any().description.likeIgnoreCase(term))
        .or(query.slug.likeIgnoreCase(term))
        .or(query.id.likeIgnoreCase(term));
  }

//  public BooleanExpression withChildId(String childId) {
//    return query.parents.any().child.id.eq(childId);
//  }
}
