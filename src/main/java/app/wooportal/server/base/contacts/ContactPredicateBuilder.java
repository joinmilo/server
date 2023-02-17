package app.wooportal.server.base.contacts;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ContactPredicateBuilder extends PredicateBuilder<QContactEntity, ContactEntity> {

  public ContactPredicateBuilder() {
    super(QContactEntity.contactEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
