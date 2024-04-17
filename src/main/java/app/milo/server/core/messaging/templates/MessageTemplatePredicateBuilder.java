package app.milo.server.core.messaging.templates;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class MessageTemplatePredicateBuilder
    extends PredicateBuilder<QMessageTemplateEntity, MessageTemplateEntity> {

  public MessageTemplatePredicateBuilder() {
    super(QMessageTemplateEntity.messageTemplateEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term).or(query.id.likeIgnoreCase(term))
        .or(query.translatables.any().content.likeIgnoreCase(term));
  }

  public BooleanExpression withName(String name) {
    return query.name.eq(name);
  }
}
