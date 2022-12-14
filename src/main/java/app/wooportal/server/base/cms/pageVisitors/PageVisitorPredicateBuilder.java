package app.wooportal.server.base.cms.pageVisitors;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.base.contact.base.QContactEntity;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PageVisitorPredicateBuilder extends PredicateBuilder<QContactEntity, PageVisitorEntity> {

  public PageVisitorPredicateBuilder() {
    super(QContactEntity.contactEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
