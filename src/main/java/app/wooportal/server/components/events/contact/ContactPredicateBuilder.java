package app.wooportal.server.components.events.contact;

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
    return query.name.likeIgnoreCase(term)
        .or(query.phone.likeIgnoreCase(term))
        .or(query.website.likeIgnoreCase(term))
        .or(query.mail.likeIgnoreCase(term));
  }
}
