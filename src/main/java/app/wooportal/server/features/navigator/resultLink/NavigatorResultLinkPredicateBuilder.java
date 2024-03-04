package app.wooportal.server.features.navigator.resultLink;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class NavigatorResultLinkPredicateBuilder extends PredicateBuilder<QNavigatorResultLinkEntity, NavigatorResultLinkEntity> {

  public NavigatorResultLinkPredicateBuilder() {
    super(QNavigatorResultLinkEntity.navigatorResultLinkEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().name.likeIgnoreCase(term)
        .or(query.id.likeIgnoreCase(term));
  }
  
}
