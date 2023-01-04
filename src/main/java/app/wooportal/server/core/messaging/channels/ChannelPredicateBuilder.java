package app.wooportal.server.core.messaging.channels;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;
import app.wooportal.server.core.messaging.channel.QChannelEntity;

@Service
public class ChannelPredicateBuilder extends PredicateBuilder<QChannelEntity, ChannelEntity> {

  public ChannelPredicateBuilder() {
    super(QChannelEntity.channelEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }
}
